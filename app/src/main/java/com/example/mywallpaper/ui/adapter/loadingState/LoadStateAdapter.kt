package com.example.mywallpaper.ui.adapter.loadingState

import android.view.ViewGroup
import androidx.paging.LoadState

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class LoadStateAdapter(
    private val retry: () -> Unit
) :androidx.paging.LoadStateAdapter<LoadStateViewHolder>(){
   override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
      holder.bind(loadState)
   }

   override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
      return LoadStateViewHolder.create(parent, retry)
   }
}