package com.example.retrofitdaggersealed.network

import com.example.retrofitdaggersealed.models.PostItem
import retrofit2.http.GET

interface ApiServer {

    @GET("posts")
    suspend fun getPost(): List<PostItem>
}