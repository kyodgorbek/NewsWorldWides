package com.example.newsworldwide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible


import com.example.newsworldwide.databinding.ActivityMainBinding
import com.example.newsworldwide.model.Article
import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.ui.viewmodel.NewsViewModel
import com.example.newsworldwide.ui.viewmodel.adapter.NewsAdapter

import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<NewsViewModel>()

    private val adapter = NewsAdapter()


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Add adapter in recyclerView
        binding.recyclerView.adapter = adapter

        // listening new data
        viewModel.news.observe(this) { response ->
            adapter.update(response.articles as MutableList<Article>)
        }

        viewModel.progress.observe(this){  showLoading ->
            binding.progressBar.isVisible = showLoading
        }

    }
}