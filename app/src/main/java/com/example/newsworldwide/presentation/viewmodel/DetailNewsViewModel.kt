package com.example.newsworldwide.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsworldwide.domain.use_case.DetailNewsUseCase
import com.example.newsworldwide.domain.utils.fold
import com.example.newsworldwide.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailNewsViewModel (private val useCase: DetailNewsUseCase) : ViewModel() {


    var news= MutableLiveData<NewsResponse>()
    // Expose to the outside world

    // Expose to the outside world
    val error = MutableLiveData<String>()
    var progress = MutableLiveData(false)

    init{
        getDetailNews()
    }
    private fun getDetailNews() {
        viewModelScope.launch(Dispatchers.IO) {
            progress.postValue(true)
            useCase.invoke()
                .fold({ newsResponse ->
                    news.postValue(newsResponse)
                }, {
                    error.postValue(it.message)
                })
            progress.postValue(false)
        }
    }




}