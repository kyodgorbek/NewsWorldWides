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


    private var _newsResponse= MutableLiveData<Result<NewsResponse>>()
    // Expose to the outside world
    val news: LiveData<Result<NewsResponse>> = _newsResponse
 var progress:MutableLiveData<Boolean> = MutableLiveData(false)


    @UiThread
    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
  progress.value = true
            try {
                val response = repository.getNews().body()!!
                _newsResponse.postValue(Result.Success(response))
            } catch (ioexception: IOException) {
                _newsResponse.postValue(Result.Failure("Opps  please retry", ioexception))
            } catch (httpException: HttpException) {
                _newsResponse.postValue(Result.Failure("[HTTP] error please retry", httpException))
            }finally {
                progress.postValue(false)
            }
        }
    }




}