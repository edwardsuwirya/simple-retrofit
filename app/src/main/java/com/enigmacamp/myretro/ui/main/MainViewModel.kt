package com.enigmacamp.myretro.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enigmacamp.myretro.data.repository.PostingRepo
import com.enigmacamp.myretro.utils.AppResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class MainViewModel(private val repository: PostingRepo) : ViewModel() {
    fun getPost() = liveData(Dispatchers.IO) {
        emit(AppResource.Loading)
        try {
            val response = repository.getPost()
            delay(1000)
            if (response.isSuccessful) {
                emit(AppResource.Success(data = response.body()))
            } else {
                emit(
                    AppResource.Error(
                        data = null,
                        message = response.errorBody().toString() ?: "Error Occured"
                    )
                )
            }
        } catch (e: Exception) {
            emit(AppResource.Error(data = null, message = e.message ?: "Error Occured"))
        }
    }
}