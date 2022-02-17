package com.example.newsworldwide.data.internet

import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.domain.utils.Constants
import retrofit2.http.GET

interface NewsInterface {

    @GET(Constants.BBC_URL)
    suspend fun getNews(): NewsResponse
    @GET(Constants.BBC_URL)
    suspend fun getDetailNews(): NewsResponse

}