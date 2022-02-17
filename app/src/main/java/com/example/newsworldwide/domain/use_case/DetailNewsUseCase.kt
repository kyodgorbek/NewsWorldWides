package com.example.newsworldwide.domain.use_case

import com.example.newsworldwide.domain.repository.NewsRepository

class DetailNewsUseCase(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke() = newsRepository.getDetailNews()
}