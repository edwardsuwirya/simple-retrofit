package com.enigmacamp.myretro.data.repository

import com.enigmacamp.myretro.data.api.RetrofitInstance
import com.enigmacamp.myretro.data.models.Post

class JsonPlaceHolderRepo {
    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}