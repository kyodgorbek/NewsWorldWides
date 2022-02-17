package com.example.newsworldwide.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.domain.use_case.BBCNewsResponseUseCase
import com.example.newsworldwide.domain.utils.fold

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BBCNewsViewModel(private val useCase: BBCNewsResponseUseCase) : ViewModel() {


     var news= MutableLiveData<NewsResponse>()
    // Expose to the outside world

    // Expose to the outside world
    val error = MutableLiveData<String>()
    var progress = MutableLiveData(false)

init{
    getNews()
}
    private fun getNews() {
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