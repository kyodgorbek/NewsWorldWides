package com.example.newsworldwide.ui

import android.os.Bundle
import android.view.View
import androidx.core.app.Person.fromBundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

import com.example.newsworldwide.R
import com.example.newsworldwide.databinding.DetailFragmentBinding
import com.example.newsworldwide.databinding.NewsFragmentBinding
import com.example.newsworldwide.domain.utils.parseDate
import com.example.newsworldwide.domain.utils.userFormat

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private var _binding: DetailFragmentBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.inflate(layoutInflater)
        val article = args.article
        binding.title.text = article.title
        article.publishedAt.parseDate()?.let {
            binding.articleDates.text = it.userFormat()
        }





        }
    }

