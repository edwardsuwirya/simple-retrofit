package com.enigmacamp.myretro.utils

sealed class AppResource<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : AppResource<T>(data)
    object Loading : AppResource<Nothing>()
    class Error<T>(message: String, data: T? = null) : AppResource<T>(data, message)
}
