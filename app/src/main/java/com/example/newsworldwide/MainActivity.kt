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

    private var article : MutableList<Article>? = null
    private lateinit var newsResponse: NewsResponse


   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.news.observe(this) { articleList ->
            binding.recyclerView.adapter = article?.let { NewsAdapter(it) }
            viewModel.progress.observe(this){  showLoading ->
                binding.progressBar.isVisible = true
            }

        }

    }
}