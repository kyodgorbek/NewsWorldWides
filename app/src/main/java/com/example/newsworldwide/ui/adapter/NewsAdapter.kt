package com.example.newsworldwide.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsworldwide.databinding.NewsItemBinding
import com.example.newsworldwide.databinding.NewsItemBinding.*
import com.example.newsworldwide.model.Article

class NewsAdapter(private var itemList: MutableList<Article> = mutableListOf<Article>()): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun getItemCount(): Int = itemList.size

    // update you data in recycler
    fun update(itemList: MutableList<Article>) {
        this.itemList = itemList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val itemBinding = inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        var articleItems = itemList[position]
        holder.bind(articleItems)
    }

    class NewsViewHolder(private val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(articles: Article) {
            itemBinding.articleDescription.text = articles.description
            itemBinding.articleTitle.text = articles.title
            itemBinding.article = articles

        }
    }

}