package com.example.newsworldwide.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsworldwide.databinding.NewsItemBinding
import com.example.newsworldwide.databinding.NewsItemBinding.*
import com.example.newsworldwide.domain.utils.parseDate
import com.example.newsworldwide.domain.utils.userFormat
import com.example.newsworldwide.model.Article
import com.example.newsworldwide.ui.NewsFragment

class NewsAdapter(private val onClick: (Article) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem

        }
    }
 var differ = AsyncListDiffer(this,differCallback)
    // update you data in recycler


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val itemBinding = inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        var articleItems = differ.currentList[position]
        holder.bind(articleItems)
        holder.itemView.setOnClickListener { onClick(articleItems) }

    }

    class NewsViewHolder(private val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(articles: Article) {
            itemBinding.articleDescription.text = articles.description
            itemBinding.articleTitle.text = articles.title
            articles.publishedAt.parseDate()?.let {
                itemBinding.articleDate.text = it.userFormat()
            }
            itemBinding.article = articles

        }
    }

}