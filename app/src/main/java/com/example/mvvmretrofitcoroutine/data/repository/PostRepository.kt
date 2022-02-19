package com.example.mvvmretrofitcoroutine.data.repository

import com.example.mvvmretrofitcoroutine.data.api.RetrofitService
import com.example.mvvmretrofitcoroutine.data.api.RetrofitServiceInterface

class PostRepository(private var retrofitService: RetrofitService)  {

    fun getAllPosts() = retrofitService.buildService(RetrofitServiceInterface::class.java).getPosts()

}