package com.example.mywallpaper.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mywallpaper.R
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.fragments.base.BaseFragment
import com.example.mywallpaper.ui.viewmodel.RandomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RandomFragment : BaseFragment() {

   private val viewModel: RandomViewModel by viewModels()
   override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

   override fun initViewModel() {
      lifecycleScope.launch(Dispatchers.IO) {
         viewModel.randomPage.collectLatest {
            recyclerViewAdapter.submitData(it)
         }
      }
   }
}