package com.example.retrofitdaggersealed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdaggersealed.R
import com.example.retrofitdaggersealed.models.PostItem

class PostAdapter(val context: Context, var postList: List<PostItem>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       val list = postList[position]

        holder.title.text = list.body
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.textViewId)
    }

    fun setData(postList: List<PostItem>){
        this.postList = postList
        notifyDataSetChanged()
    }


}