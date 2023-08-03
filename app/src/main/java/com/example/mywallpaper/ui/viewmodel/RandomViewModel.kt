package com.example.mywallpaper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mywallpaper.data.remote.paging.RandomPagingSource
import com.example.mywallpaper.repository.WallRepository

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

class RandomViewModel : ViewModel() {
   private val repository = WallRepository()
   val randomPage = Pager(config = PagingConfig(pageSize = 30),
                          pagingSourceFactory = { RandomPagingSource(repository.api()) }
   ).flow.cachedIn(viewModelScope)


}