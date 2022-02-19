package com.example.mvvmretrofitcoroutine.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretrofitcoroutine.R
import com.example.mvvmretrofitcoroutine.data.api.RetrofitService
import com.example.mvvmretrofitcoroutine.data.repository.UserRepository
import com.example.mvvmretrofitcoroutine.domain.UserUseCase
import com.example.mvvmretrofitcoroutine.presentation.adapters.CompanyAdapter
import com.example.mvvmretrofitcoroutine.presentation.viewmodels.company.UserCompaniesViewModel
import com.example.mvvmretrofitcoroutine.presentation.viewmodels.company.UserCompaniesViewModelFactory

class CompaniesFragment : Fragment() {

    private lateinit var companiesRecyclerView: RecyclerView
    private val companyAdapter = CompanyAdapter()
    private lateinit var userCompaniesViewModel: UserCompaniesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_companies, container, false)
        companiesRecyclerView = rootView.findViewById(R.id.companies_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSetUp()
        setUpObserver()
    }

    private fun initializeSetUp() {
        companiesRecyclerView.adapter = companyAdapter
        companiesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val userRepository = UserRepository(RetrofitService)
        val userUseCase = UserUseCase(userRepository)

        userCompaniesViewModel =
            ViewModelProvider(this, UserCompaniesViewModelFactory(userUseCase)).get(
                UserCompaniesViewModel::class.java)
    }

    private fun setUpObserver() {
        userCompaniesViewModel.companiesList.observe(viewLifecycleOwner, Observer {
            companyAdapter.updateCompanies(it)
        })
    }
}