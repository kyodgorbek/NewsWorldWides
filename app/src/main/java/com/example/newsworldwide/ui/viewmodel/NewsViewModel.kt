package com.example.newsworldwide.ui.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsworldwide.model.NewsResponse
import com.example.newsworldwide.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.example.newsworldwide.utils.Result

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {


    private var _newsResponse= MutableLiveData<NewsResponse>()
    // Expose to the outside world
    val news: LiveData<NewsResponse> = _newsResponse
 var progress:MutableLiveData<Boolean> = MutableLiveData(false)

init{
    getNews()
}
    @UiThread
    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
  progress.postValue(true)
            try {
                val response = repository.getNews().body()!!
                _newsResponse.postValue(response)

            }finally {
                progress.postValue(false)
            }
        }
    }




}