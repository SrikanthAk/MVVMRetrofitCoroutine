package com.example.mvvmretrofitcoroutine.data.repository

import com.example.mvvmretrofitcoroutine.data.api.RetrofitService
import com.example.mvvmretrofitcoroutine.data.api.RetrofitServiceInterface

class UserRepository(private var retrofitService: RetrofitService)  {

    suspend fun getAllUsers() = retrofitService.buildService(RetrofitServiceInterface::class.java).getUsers()
}