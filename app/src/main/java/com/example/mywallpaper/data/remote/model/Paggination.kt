package com.example.mywallpaper.data.remote.model


import com.google.gson.annotations.SerializedName

data class Paggination(
    @SerializedName("next")
    val next: Next?
)