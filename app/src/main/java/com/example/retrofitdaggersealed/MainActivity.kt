package com.example.retrofitdaggersealed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdaggersealed.adapter.PostAdapter
import com.example.retrofitdaggersealed.util.ApiState
import com.example.retrofitdaggersealed.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var postAdapter: PostAdapter
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycelVeiwid)
        progressBar = findViewById(R.id.progressBarId)

        setRecycleView()

        postViewModel.getPost()

        lifecycleScope.launchWhenStarted {
            postViewModel._postStateFlow.collect {
                    when(it){
                        is ApiState.Loading->{
                            recyclerView.isVisible = false
                            progressBar.isVisible = true
                        }
                        is ApiState.Failure->{
                            recyclerView.isVisible = false
                            progressBar.isVisible = false

                            Log.e("tag", "error :${it.msg}")
                        }
                        is ApiState.Success->{
                            recyclerView.isVisible = true
                            progressBar.isVisible = false
                            postAdapter.setData(it.data)
                        }
                        is ApiState.Empty->{

                        }
                    }
            }
        }
    }

    private fun setRecycleView() {
        postAdapter = PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}