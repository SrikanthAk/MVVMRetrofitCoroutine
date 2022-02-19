package com.example.mvvmretrofitcoroutine.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretrofitcoroutine.R
import com.example.mvvmretrofitcoroutine.data.model.Post

class PostsAdapter():   RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var posts = mutableListOf<Post>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userId: TextView = view.findViewById(R.id.user_id)
        val postId: TextView = view.findViewById(R.id.post_id)
        val postTitle: TextView = view.findViewById(R.id.post_title)
        val postBody: TextView = view.findViewById(R.id.post_body)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.post_tem, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.userId.text = posts[position].userId.toString()
        viewHolder.postId.text = posts[position].postId.toString()
        viewHolder.postTitle.text = posts[position].postTitle
        viewHolder.postBody.text = posts[position].postBody
    }


    fun updatePosts(posts: List<Post>){
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    override fun getItemCount() = posts.size
}