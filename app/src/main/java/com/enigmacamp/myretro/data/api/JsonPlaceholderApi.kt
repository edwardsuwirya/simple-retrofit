package com.enigmacamp.myretro.data.api

import com.enigmacamp.myretro.data.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("postsq/1")
    suspend fun getPost(): Response<Post>
}