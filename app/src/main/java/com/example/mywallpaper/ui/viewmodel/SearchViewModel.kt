package com.example.mywallpaper.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.data.remote.paging.SearchPagingSource
import com.example.mywallpaper.repository.WallRepository
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

class SearchViewModel : ViewModel() {

   private val repository = WallRepository()
   var data: MutableLiveData<PagingData<Data>> = MutableLiveData()

   fun searchFromApi(keyWord: String) {
      viewModelScope.launch {
         Pager(config = PagingConfig(pageSize = 30),
               pagingSourceFactory = { SearchPagingSource(repository.api(), keyWord) }
         ).flow.cachedIn(viewModelScope).collect {
            data.postValue(it)
         }
      }
   }
}