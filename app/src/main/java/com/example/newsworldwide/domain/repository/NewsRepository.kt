package com.example.newsworldwide.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsworldwide.data.internet.NewsInterface
import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.domain.utils.Result
import com.example.newsworldwide.domain.utils.parseDate


class NewsRepository(
   private val apiInterface:NewsInterface
){

@RequiresApi(Build.VERSION_CODES.N)
suspend fun getNews() : Result<NewsResponse>{
   return try {
       val response = apiInterface.getNews()
      response.articles.sortedWith(Comparator.comparing {
         it.publishedAt.parseDate()
      })
      Result.Success(response)
   } catch (ex: Exception) {
      Result.Error(ex)
   }
}

   @RequiresApi(Build.VERSION_CODES.N)
   suspend fun getDetailNews() : Result<NewsResponse>{
      return try {
         val response = apiInterface.getDetailNews()
         response.articles.sortedWith(Comparator.comparing {
            it.publishedAt.parseDate()
         })

         Result.Success(response)
      } catch (ex: Exception) {
         Result.Error(ex)
      }
   }


}



