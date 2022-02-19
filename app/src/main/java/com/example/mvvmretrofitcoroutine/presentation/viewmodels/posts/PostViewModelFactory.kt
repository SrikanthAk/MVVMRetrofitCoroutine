package com.example.mvvmretrofitcoroutine.presentation.viewmodels.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitcoroutine.domain.PostUseCase

class PostViewModelFactory constructor(private val postUseCase: PostUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            PostsViewModel(this.postUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}