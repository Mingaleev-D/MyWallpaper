package com.example.mywallpaper.data.remote.model


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Int?
)