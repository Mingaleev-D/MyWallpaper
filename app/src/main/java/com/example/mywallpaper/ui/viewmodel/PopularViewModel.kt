package com.example.mywallpaper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mywallpaper.data.remote.paging.PopularPagingSource
import com.example.mywallpaper.repository.WallRepository

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

class PopularViewModel : ViewModel() {

   private val repository = WallRepository()

   val popularPage = Pager(config = PagingConfig(pageSize = 30),
                           pagingSourceFactory = {
                              PopularPagingSource(repository.api())
                           }
   ).flow.cachedIn(viewModelScope)
}