package com.example.mywallpaper.repository

import com.example.mywallpaper.data.remote.ApiInstance
import com.example.mywallpaper.data.remote.ApiService

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class WallRepository {
   fun api(): ApiService = ApiInstance.api
}