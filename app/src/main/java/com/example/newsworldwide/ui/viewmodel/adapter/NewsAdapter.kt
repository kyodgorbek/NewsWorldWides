package com.example.newsworldwide.ui.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsworldwide.databinding.NewsItemBinding
import com.example.newsworldwide.databinding.NewsItemBinding.*
import com.example.newsworldwide.model.Article

class NewsAdapter(private val article: MutableList<Article>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val itemBinding = inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        var articleItems = article[position]
        holder.bind(articleItems)
    }

    override fun getItemCount(): Int = article.size
    class NewsViewHolder(private val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(articles: Article) {
            itemBinding.articleDescription.text = articles.description
            itemBinding.articleTitle.text = articles.title

        }
    }

}