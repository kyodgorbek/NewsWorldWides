package com.example.newsworldwide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible


import com.example.newsworldwide.databinding.ActivityMainBinding
import com.example.newsworldwide.model.Article
import com.example.newsworldwide.ui.viewmodel.NewsViewModel
import com.example.newsworldwide.ui.adapter.NewsAdapter

import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}