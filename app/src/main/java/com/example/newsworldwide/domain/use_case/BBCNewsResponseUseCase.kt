package com.example.newsworldwide.domain.use_case

import com.example.newsworldwide.domain.repository.NewsRepository

class BBCNewsResponseUseCase(
    private val newsRepository: NewsRepository
) {
     suspend operator fun invoke() = newsRepository.getNews()
}