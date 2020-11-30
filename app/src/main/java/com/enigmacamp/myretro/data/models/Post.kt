package com.enigmacamp.myretro.data.models

import com.google.gson.annotations.SerializedName

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    @SerializedName("userId")
    val myUserId: Int
)