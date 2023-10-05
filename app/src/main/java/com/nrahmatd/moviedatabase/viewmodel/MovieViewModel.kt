package com.nrahmatd.moviedatabase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrahmatd.moviedatabase.network.ApiRepository
import com.nrahmatd.moviedatabase.network.exception.ApiException
import com.nrahmatd.moviedatabase.network.exception.NoInternetException
import com.nrahmatd.moviedatabase.utils.ResponseHelper
import com.nrahmatd.moviedatabase.utils.setRequest
import com.nrahmatd.moviedatabase.utils.setResponse
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: ApiRepository) : ViewModel() {

    var response = MutableLiveData<ResponseHelper>()

    fun getMovies(code_request: Int) = viewModelScope.launch {
        setResponse(response, ResponseHelper.LOADING, true)
        try {
            val request = repository.getMovies()
            setRequest(response, request, code_request)
            setResponse(response, ResponseHelper.LOADING, false)
        } catch (e: Exception) {
            if (e is ApiException || e is NoInternetException) {
                setResponse(response, ResponseHelper.ERROR, e.message!!)
                setResponse(response, ResponseHelper.LOADING, false)
            }
        }
    }
}
