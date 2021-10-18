package com.example.retrofitdaggersealed.util

import com.example.retrofitdaggersealed.models.PostItem

sealed class ApiState{

    object Loading: ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data : List<PostItem>): ApiState()
    object Empty : ApiState()
}
