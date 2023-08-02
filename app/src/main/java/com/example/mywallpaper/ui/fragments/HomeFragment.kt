package com.example.mywallpaper.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaper.databinding.FragmentHomeBinding
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.fragments.base.BaseFragment
import com.example.mywallpaper.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
   override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter()

   private val viewModel: HomeViewModel by viewModels()
   override fun initViewModel() {
      lifecycleScope.launch {
         viewModel.homePage.collectLatest {
            recyclerViewAdapter.submitData(it)
         }
      }
   }

   override fun initRecyclerView() {
      val layoManager = GridLayoutManager(context, 3)
      binding.wallRecyclerView.apply {
         layoutManager = layoManager
         adapter = recyclerViewAdapter
      }
   }


}