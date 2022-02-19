package com.example.mvvmretrofitcoroutine.data.api

import com.example.mvvmretrofitcoroutine.data.model.Post
import com.example.mvvmretrofitcoroutine.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServiceInterface {

    @GET("posts")
    fun getPosts(): Call<List<Post>>


    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}