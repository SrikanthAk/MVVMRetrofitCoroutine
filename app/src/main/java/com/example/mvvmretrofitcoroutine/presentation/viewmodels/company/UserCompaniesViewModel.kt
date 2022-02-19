package com.example.mvvmretrofitcoroutine.presentation.viewmodels.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmretrofitcoroutine.data.model.Company
import com.example.mvvmretrofitcoroutine.data.model.User
import com.example.mvvmretrofitcoroutine.domain.UserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserCompaniesViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _companiesList = MutableLiveData<List<Company>>()
    val companiesList get() = _companiesList
    var job: Job? = null

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = userUseCase.getUserCompanies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { addCompanies(it) }
                } else {
                    //ToDo::show error
                }
            }
        }

    }


    private fun addCompanies(users: List<User>){
        val companies = mutableListOf<Company>()
        for (i in users.indices){
            users[i].company?.let { companies.add(i, it) }
        }
        _companiesList.postValue(companies)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}