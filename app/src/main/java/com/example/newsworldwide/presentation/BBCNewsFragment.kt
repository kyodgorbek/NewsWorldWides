package com.example.newsworldwide.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsworldwide.R
import com.example.newsworldwide.databinding.BbcnewsFragmentBinding
import com.example.newsworldwide.model.Article
import com.example.newsworldwide.presentation.adapter.NewsAdapter
import com.example.newsworldwide.presentation.viewmodel.BBCNewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BBCNewsFragment : Fragment(R.layout.bbcnews_fragment) {

    private val viewModel by viewModel<BBCNewsViewModel>()
    private val adapter = NewsAdapter() { article ->
        findNavController().navigate(
            BBCNewsFragmentDirections.actionBbcnewsFragmentToDetailFragment(article)
        )
    }

    private var _binding: BbcnewsFragmentBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BbcnewsFragmentBinding.inflate(layoutInflater)
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

