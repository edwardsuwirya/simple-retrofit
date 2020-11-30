package com.enigmacamp.myretro.data.api

import com.enigmacamp.myretro.data.models.Post
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("posts/1")
    suspend fun getPost(): Post
}