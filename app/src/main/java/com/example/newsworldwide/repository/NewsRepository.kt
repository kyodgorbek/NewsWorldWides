package com.example.newsworldwide.repository

import com.example.newsworldwide.internet.NewsInterface


class NewsRepository(
   private val apiInterface:NewsInterface
){

suspend fun getNews() = apiInterface.getNews()


}



