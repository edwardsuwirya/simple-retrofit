package com.enigmacamp.myretro.utils

sealed class AppResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : AppResource<T>(data)
    class Loading() : AppResource<Nothing>()
    class Error<T>(message: String, data: T? = null) : AppResource<T>(data, message)
}
