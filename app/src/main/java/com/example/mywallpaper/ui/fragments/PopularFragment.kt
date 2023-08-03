package com.example.mywallpaper.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.fragments.base.BaseFragment
import com.example.mywallpaper.ui.viewmodel.PopularViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment : BaseFragment() {

   private val viewModel: PopularViewModel by viewModels()
   override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

   override fun initViewModel() {
      lifecycleScope.launch {
         viewModel.popularPage.collectLatest {
            recyclerViewAdapter.submitData(it)
         }
      }

   }
}