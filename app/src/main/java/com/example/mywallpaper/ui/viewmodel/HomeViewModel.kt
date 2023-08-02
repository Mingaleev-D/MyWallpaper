package com.example.mywallpaper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mywallpaper.data.remote.paging.HomePagingSource
import com.example.mywallpaper.repository.WallRepository

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class HomeViewModel : ViewModel() {
   val repository = WallRepository()

   val homePage = Pager(
       config = PagingConfig(pageSize = 30),
       pagingSourceFactory = {
          HomePagingSource(repository.api())
       }
   ).flow.cachedIn(viewModelScope)
}