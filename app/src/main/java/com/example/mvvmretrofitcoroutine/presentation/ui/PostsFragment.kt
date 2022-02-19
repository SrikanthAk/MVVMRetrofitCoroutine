package com.example.mvvmretrofitcoroutine.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretrofitcoroutine.R
import com.example.mvvmretrofitcoroutine.data.api.RetrofitService
import com.example.mvvmretrofitcoroutine.data.repository.PostRepository
import com.example.mvvmretrofitcoroutine.data.repository.UserRepository
import com.example.mvvmretrofitcoroutine.domain.PostUseCase
import com.example.mvvmretrofitcoroutine.domain.UserUseCase
import com.example.mvvmretrofitcoroutine.presentation.adapters.PostsAdapter
import com.example.mvvmretrofitcoroutine.presentation.viewmodels.company.UserCompaniesViewModel
import com.example.mvvmretrofitcoroutine.presentation.viewmodels.company.UserCompaniesViewModelFactory
import com.example.mvvmretrofitcoroutine.presentation.viewmodels.posts.PostViewModelFactory
import com.example.mvvmretrofitcoroutine.presentation.viewmodels.posts.PostsViewModel

class PostsFragment : Fragment() {

    private lateinit var postsRecyclerView: RecyclerView
    private var postsAdapter = PostsAdapter()
    private lateinit var postsViewModel: PostsViewModel

    private lateinit var userCompaniesViewModel: UserCompaniesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_posts, container, false)
        postsRecyclerView = rootView.findViewById(R.id.posts_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSetUp()
        setUpObserver()
    }

    private fun initializeSetUp() {
        postsRecyclerView.adapter = postsAdapter
        postsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //ToDO:: need to add DI framework to inject all these dependencies
        val postRepository = PostRepository(RetrofitService)
        val postUseCase = PostUseCase(postRepository)
        postsViewModel = ViewModelProvider(this, PostViewModelFactory(postUseCase)).get(
            PostsViewModel::class.java)
    }

    private fun setUpObserver() {
        postsViewModel.postsList.observe(viewLifecycleOwner, Observer {
            postsAdapter.updatePosts(it)
        })
    }
}