package com.enigmacamp.myretro.ui.main

import androidx.lifecycle.*
import com.enigmacamp.myretro.data.models.Post
import com.enigmacamp.myretro.data.repository.PostingRepo
import com.enigmacamp.myretro.utils.AppResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostingRepo) : ViewModel() {
    private var _postingLiveData = MutableLiveData<AppResource<Post>>()
    val postingLiveData: LiveData<AppResource<Post>>
        get() = _postingLiveData

    fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            _postingLiveData.postValue(AppResource.Loading)
            try {
                val response = repository.getPost()
                delay(1000)
                if (response.isSuccessful) {
                    _postingLiveData.postValue(AppResource.Success(data = response.body()))
                } else {
                    _postingLiveData.postValue(
                        AppResource.Error(
                            data = null,
                            message = response.errorBody().toString() ?: "Error Occured"
                        )
                    )
                }
            } catch (e: Exception) {
                _postingLiveData.postValue(
                    AppResource.Error(
                        data = null,
                        message = e.message ?: "Error Occured"
                    )
                )
            }
        }

    }
}