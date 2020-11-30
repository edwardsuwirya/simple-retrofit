package com.enigmacamp.myretro.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enigmacamp.myretro.data.repository.JsonPlaceHolderRepo
import com.enigmacamp.myretro.utils.AppResource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: JsonPlaceHolderRepo) : ViewModel() {
    fun getPost() = liveData(Dispatchers.IO) {
        emit(AppResource.loading(data = null))
        try {
            val response = repository.getPost()
            if (response.isSuccessful) {
                emit(AppResource.success(data = response.body()))
            } else {
                emit(
                    AppResource.error(
                        data = null,
                        message = response.errorBody().toString() ?: "Error Occured"
                    )
                )
            }
        } catch (e: Exception) {
            emit(AppResource.error(data = null, message = e.message ?: "Error Occured"))
        }
    }
}