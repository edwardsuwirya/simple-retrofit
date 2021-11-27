package com.enigmacamp.myretro.data.repository

import com.enigmacamp.myretro.data.api.RetrofitInstance
import com.enigmacamp.myretro.data.models.Post
import retrofit2.Response

class JsonPlaceHolderRepo : PostingRepo {

    override suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}