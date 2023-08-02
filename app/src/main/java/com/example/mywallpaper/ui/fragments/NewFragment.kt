package com.example.mywallpaper.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.fragments.base.BaseFragment
import com.example.mywallpaper.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewFragment : BaseFragment() {
   override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

   private val viewModel: HomeViewModel by viewModels()
   override fun initViewModel() {
      lifecycleScope.launch {
         viewModel.homePage.collectLatest {
            recyclerViewAdapter.submitData(it)
         }
      }
   }
}