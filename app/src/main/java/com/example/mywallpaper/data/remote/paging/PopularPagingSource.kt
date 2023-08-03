package com.example.mywallpaper.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mywallpaper.data.remote.ApiService
import com.example.mywallpaper.data.remote.model.Data

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

class PopularPagingSource(private val apiService: ApiService) :
    PagingSource<Int, Data>() {
   override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
      return state.anchorPosition?.let { anchorPosition ->
         val anchorPage = state.closestPageToPosition(anchorPosition)
         anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
      }
   }


   override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
      return try {
         val nextPage = params.key ?: FIRST_PAGE_INDEX
         val responsePopular = apiService.getPopularFromApi(nextPage)

         LoadResult.Page(
             data = responsePopular.data,
             prevKey = null,
             nextKey = responsePopular.paggination?.next?.page,
         )
      } catch (e: Exception) {
         LoadResult.Error(e)
      }
   }

   companion object {
      private const val FIRST_PAGE_INDEX = 1
   }

}