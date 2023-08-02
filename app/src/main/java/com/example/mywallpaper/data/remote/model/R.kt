package com.example.mywallpaper.data.remote.model


import com.google.gson.annotations.SerializedName

data class R(
    @SerializedName("coordinates")
    val coordinates: List<Double?>?,
    @SerializedName("type")
    val type: String?
)