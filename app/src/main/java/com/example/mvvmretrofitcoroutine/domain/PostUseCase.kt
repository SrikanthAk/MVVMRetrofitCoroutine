package com.example.mvvmretrofitcoroutine.domain

import com.example.mvvmretrofitcoroutine.data.repository.PostRepository

class PostUseCase(private var postRepository: PostRepository) {
    fun getPosts() = postRepository.getAllPosts()
}