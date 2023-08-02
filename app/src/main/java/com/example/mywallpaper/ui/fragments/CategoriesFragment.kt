package com.example.mywallpaper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaper.data.remote.model.Category
import com.example.mywallpaper.databinding.FragmentCategoriesBinding
import com.example.mywallpaper.ui.adapter.recyclerview.CategoriesAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.CategoryInteractionListener
import com.example.mywallpaper.ui.adapter.recyclerview.common.ApiListCategory

class CategoriesFragment : Fragment(), CategoryInteractionListener {

   private lateinit var binding: FragmentCategoriesBinding

   private lateinit var recyclerViewAdapter: CategoriesAdapter
   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?,
   ): View {
      binding = FragmentCategoriesBinding.inflate(inflater, container, false)

      recyclerAdapter()

      return binding.root
   }

   private fun recyclerAdapter() {
      val layoutManager = GridLayoutManager(context, 2)
      recyclerViewAdapter = CategoriesAdapter(ApiListCategory.list,this)
      binding.categoriesRecyclerView.layoutManager = layoutManager
      binding.categoriesRecyclerView.adapter = recyclerViewAdapter
   }

   override fun onClickCategory(category: Category, view: View) {
      val action = MainFragmentDirections.actionMainFragmentToSpecificCategoryFragment(category.categoryName)
      Navigation.findNavController(view)
          .navigate(action)
   }
}