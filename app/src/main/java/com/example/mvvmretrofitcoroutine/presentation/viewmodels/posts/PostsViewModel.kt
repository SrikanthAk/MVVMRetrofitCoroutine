package com.example.mvvmretrofitcoroutine.presentation.viewmodels.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmretrofitcoroutine.data.model.Post
import com.example.mvvmretrofitcoroutine.data.repository.PostRepository
import com.example.mvvmretrofitcoroutine.domain.PostUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel(private val postUseCase: PostUseCase) : ViewModel() {
    private val _postsList = MutableLiveData<List<Post>>()
    internal val postsList: LiveData<List<Post>> get() = _postsList

    init {
        GlobalScope.launch {
            getAllPostsFromServer()
        }
    }

    private fun getAllPostsFromServer() {
        val response = postUseCase.getPosts()
        response.enqueue(object : Callback<List<Post>> {
            override fun onResponse(p0: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    _postsList.postValue(response.body())
                }
            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
                android.util.Log.d("MVVM","Sample")
            }
        })
    }
}