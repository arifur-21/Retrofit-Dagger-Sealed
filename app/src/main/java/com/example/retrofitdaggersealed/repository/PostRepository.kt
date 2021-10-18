package com.example.retrofitdaggersealed.repository

import com.example.retrofitdaggersealed.network.ApiServer
import com.example.retrofitdaggersealed.models.PostItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class PostRepository
    @Inject
    constructor(private val apiServer: ApiServer){

        fun getPost(): Flow<List<PostItem>> = flow {
            emit(apiServer.getPost())
        }.flowOn(Dispatchers.IO)
}