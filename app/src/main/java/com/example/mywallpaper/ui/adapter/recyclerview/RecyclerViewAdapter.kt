package com.example.mywallpaper.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.mywallpaper.R
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.databinding.ItemRecyclerViewBinding
import com.example.mywallpaper.ui.adapter.recyclerview.common.BlurHashDecoder

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class RecyclerViewAdapter(private val listener: WallInteractionListener) :
    PagingDataAdapter<Data, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

   override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
      holder.bind(getItem(position)!!)
   }

   override fun onCreateViewHolder(
       parent: ViewGroup,
       viewType: Int
   ): RecyclerViewAdapter.MyViewHolder {
      val inflater =
          LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
      return MyViewHolder(inflater)
   }

   inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      private val binding = ItemRecyclerViewBinding.bind(view)

      fun bind(data: Data) {

         val blurHashAsDrawable = BlurHashDecoder.blurHashBitmap(itemView.resources, data)

         Glide.with(itemView.context)
             .asBitmap()
             .load(data.smallImageUrl)
             .placeholder(blurHashAsDrawable)
             .centerCrop()
             .transition(BitmapTransitionOptions.withCrossFade(80))
             .error(blurHashAsDrawable)
             .into(binding.imageView)

         itemView.setOnClickListener {
            listener.onClickItem(data, it)
         }

      }

   }

   class DiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
      override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
         return oldItem.blurHash == newItem.blurHash

      }

      override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
         return oldItem == newItem

      }
   }
}