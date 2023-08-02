package com.example.mywallpaper.ui.adapter.loadingState

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallpaper.R
import com.example.mywallpaper.databinding.LoderItemBinding

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class LoadStateViewHolder (
    private val binding: LoderItemBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(binding.root){

   init {
      binding.retryButtonItem.setOnClickListener {
         retry.invoke()
      }
   }

   @SuppressLint("SetTextI18n")
   fun bind(loadState: LoadState){
      if (loadState is LoadState.Error){
         binding.errorMsg.text = "please repeat again"
      }
      binding.progressBar.isVisible = loadState is LoadState.Loading
      binding.retryButtonItem.isVisible = loadState !is LoadState.Loading
      binding.errorMsg.isVisible = loadState !is LoadState.Loading
   }

   companion object{
      fun create(parent : ViewGroup, retry: () -> Unit): LoadStateViewHolder {
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.loder_item, parent, false)
         val binding = LoderItemBinding.bind(view)
         return LoadStateViewHolder(binding,retry)
      }
   }
}