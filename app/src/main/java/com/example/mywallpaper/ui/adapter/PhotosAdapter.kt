package com.example.mywallpaper.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallpaper.R
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.databinding.ItemViewpagerBinding
import com.example.mywallpaper.ui.adapter.recyclerview.ViewPagerInterface

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class PhotosAdapter(
    private val photosList: List<Data>,
    private val listener: ViewPagerInterface,
) : RecyclerView.Adapter<PhotosAdapter.PhotosHolder>() {

   class PhotosHolder(val itemLayoutBinding: ItemViewpagerBinding) :
       RecyclerView.ViewHolder(itemLayoutBinding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {
      val itemLayoutBinding = DataBindingUtil.inflate<ItemViewpagerBinding>(
          LayoutInflater.from(parent.context), R.layout.item_viewpager, parent, false
      )
      return PhotosHolder(itemLayoutBinding)
   }


   override fun getItemCount() =
       photosList.size

   override fun onBindViewHolder(holder: PhotosHolder, position: Int) {


      val currentItem = photosList[position]
      holder.itemLayoutBinding.photo = currentItem

      listener.positionItem(currentItem, position)

      holder.itemView.setOnClickListener {
         listener.onClickCategory(currentItem, it, holder.itemLayoutBinding)
      }

   }
}