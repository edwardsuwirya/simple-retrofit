package com.enigmacamp.myretro.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.myretro.data.models.Post
import com.enigmacamp.myretro.data.repository.JsonPlaceHolderRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: JsonPlaceHolderRepo) : ViewModel() {
    var _response: MutableLiveData<Response<Post>> = MutableLiveData()

    val response: LiveData<Response<Post>>
        get() {
            return _response
        }

    fun getPost() {
        viewModelScope.launch {
            val jsonPlaceHolderResponse = repository.getPost()
            _response.value = jsonPlaceHolderResponse
        }
    }
}