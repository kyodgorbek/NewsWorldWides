package com.example.newsworldwide.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsworldwide.domain.repository.NewsRepository

class BBCNewsResponseUseCase(
    private val newsRepository: NewsRepository
) {
     @RequiresApi(Build.VERSION_CODES.N)
     suspend operator fun invoke() = newsRepository.getNews()
}