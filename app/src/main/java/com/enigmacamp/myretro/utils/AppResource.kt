package com.enigmacamp.myretro.utils

data class AppResource<out T>(val appStatus: AppStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): AppResource<T> =
            AppResource(appStatus = AppStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T, message: String?): AppResource<T> =
            AppResource(appStatus = AppStatus.ERROR, data = null, message = message)

        fun <T> loading(data: T): AppResource<T> =
            AppResource(appStatus = AppStatus.LOADING, data = data, message = null)
    }
}