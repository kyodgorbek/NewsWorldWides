package com.example.newsworldwide.domain.repository

import com.example.newsworldwide.data.internet.NewsInterface
import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.domain.utils.Result


class NewsRepository(
   private val apiInterface:NewsInterface
){

suspend fun getNews() : Result<NewsResponse>{
   return try {
       val response = apiInterface.getNews()
      Result.Success(response)
   } catch (ex: Exception) {
      Result.Error(ex)
   }
}

   suspend fun getDetailNews() : Result<NewsResponse>{
      return try {
         val response = apiInterface.getDetailNews()
         Result.Success(response)
      } catch (ex: Exception) {
         Result.Error(ex)
      }
   }


}



