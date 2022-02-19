package com.example.mvvmretrofitcoroutine.presentation.viewmodels.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitcoroutine.domain.UserUseCase

class UserCompaniesViewModelFactory constructor(private val userUseCase: UserUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserCompaniesViewModel::class.java)) {
            UserCompaniesViewModel(this.userUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}