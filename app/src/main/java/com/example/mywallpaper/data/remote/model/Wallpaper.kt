package com.example.mywallpaper.data.remote.model


import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("paggination")
    val paggination: Paggination?,
    @SerializedName("success")
    val success: Boolean?
)