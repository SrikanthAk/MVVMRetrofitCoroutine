package com.example.mvvmretrofitcoroutine.domain

import com.example.mvvmretrofitcoroutine.data.repository.UserRepository

class UserUseCase(private var userRepository: UserRepository) {

    suspend fun getUserCompanies() = userRepository.getAllUsers()

}