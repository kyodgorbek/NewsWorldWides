package com.example.newsworldwide.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import com.example.newsworldwide.R
import com.example.newsworldwide.databinding.NewsFragmentBinding
import com.example.newsworldwide.model.Article
import com.example.newsworldwide.presentation.adapter.NewsAdapter
import com.example.newsworldwide.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController

class BBCNewsFragment : Fragment(R.layout.news_fragment) {

    private val viewModel by viewModel<NewsViewModel>()
    private val adapter = NewsAdapter() { article ->
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToDetailFragment(article)
        )
    }

    private var _binding: NewsFragmentBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = NewsFragmentBinding.inflate(layoutInflater)
        //Add adapter in recyclerView
        binding.recyclerView.adapter = adapter

        // listening new data
        viewModel.news.observe(viewLifecycleOwner) { response ->
            adapter.differ.submitList(response.articles as MutableList<Article>)
        }

        viewModel.progress.observe(viewLifecycleOwner){  showLoading ->
            binding.progressBar.isVisible = showLoading
        }

    }
    }

