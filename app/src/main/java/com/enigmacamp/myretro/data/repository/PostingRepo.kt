package com.enigmacamp.myretro.data.repository

import com.enigmacamp.myretro.data.models.Post
import retrofit2.Response

interface PostingRepo {
    suspend fun getPost(): Response<Post>
}