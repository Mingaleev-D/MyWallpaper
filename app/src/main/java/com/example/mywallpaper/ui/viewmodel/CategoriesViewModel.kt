package com.example.mywallpaper.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.data.remote.paging.CategoryPagingSource
import com.example.mywallpaper.repository.WallRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

class CategoriesViewModel constructor(private var categoryID: String) : ViewModel() {
   private val repository = WallRepository()

   var data: MutableLiveData<PagingData<Data>> = MutableLiveData()

   init {
      viewModelScope.launch {
         loadCategoryToRandom(categoryID).collect {
            data.postValue(it)
         }
      }
   }

   private fun loadCategoryToRandom(category: String): Flow<PagingData<Data>> {

      return Pager(config = PagingConfig(pageSize = 30),
                   pagingSourceFactory = { CategoryPagingSource(repository.api(), category) }
      ).flow.cachedIn(viewModelScope)
   }

}