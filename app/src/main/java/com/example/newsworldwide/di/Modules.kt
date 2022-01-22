package com.example.newsworldwide.di

import android.content.Context
import com.example.newsworldwide.ui.viewmodel.NewsViewModel
import com.example.newsworldwide.internet.NewsInterface
import com.example.newsworldwide.utils.Constants

import okhttp3.Cache
import okhttp3.OkHttpClient


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

object Modules {

    val viewModels = module {
        viewModel { NewsViewModel(get()) }
    }


    private fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .header("X-API-Key", Constants.API_KEY)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logger)
            .build()
    }

    val apiModule = module {


        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .build()
        }


         fun provideCache(context: Context): Cache {
            val cacheSize: Long = 10 * 1024 * 1024
            return Cache(context.cacheDir, cacheSize)
        }


        fun provideAppService(retrofit: Retrofit): NewsInterface =
            retrofit.create(NewsInterface::class.java)
    }
}