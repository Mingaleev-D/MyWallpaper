package com.example.mywallpaper.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mywallpaper.R
import com.example.mywallpaper.data.remote.model.Category
import com.example.mywallpaper.databinding.CategoryRowBinding

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class CategoriesAdapter(
    private val category: List<Category>,
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

   class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val binding = CategoryRowBinding.bind(view)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
      return CategoryViewHolder(view)
   }

   override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
      val currentCategory = category[position]
      holder.binding.apply {
         categoryTextView.text = currentCategory.categoryName

         Glide.with(holder.itemView.context)
             .load(currentCategory.imageUrl)
             .centerCrop()
             .error(R.color.babyBlue)
             .into(categoryImageView)
      }

   }
   override fun getItemCount() = category.size
}