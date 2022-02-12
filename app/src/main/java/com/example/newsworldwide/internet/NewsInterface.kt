package com.example.newsworldwide.internet

import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.domain.utils.Constants
import retrofit2.http.GET

interface NewsInterface {

    @GET(Constants.NEWS_URL)
    suspend fun getNews(): NewsResponse

}