package com.example.mywallpaper.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.databinding.FragmentNewBinding
import com.example.mywallpaper.ui.adapter.loadingState.LoadStateAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.WallInteractionListener
import com.example.mywallpaper.ui.fragments.MainFragmentDirections

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

abstract class BaseFragment : Fragment(), WallInteractionListener {

   abstract var recyclerViewAdapter: RecyclerViewAdapter
   private lateinit var binding: FragmentNewBinding

   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?,
   ): View? {

      binding = FragmentNewBinding.inflate(inflater)

      recyclerAdapter()
      initViewModel()

      return binding.root
   }

   private fun recyclerAdapter() {
      val layoutManager = GridLayoutManager(context, 3)
      binding.wallRecyclerView.layoutManager = layoutManager
      binding.wallRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
          header = LoadStateAdapter { recyclerViewAdapter.retry() },
          footer = LoadStateAdapter { recyclerViewAdapter.retry() }
      )


      recyclerViewAdapter.addLoadStateListener { loadState ->
         binding.wallRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
         binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
         binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
         handelError(loadState)
      }
      binding.buttonRetry.setOnClickListener {
         recyclerViewAdapter.retry()
      }
   }

   private fun handelError(loadStates: CombinedLoadStates) {
      val errorState = loadStates.source.append as? LoadState.Error
                       ?: loadStates.source.prepend as? LoadState.Error

      errorState?.let {
         Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
      }
   }

   override fun onClickItem(data: Data, view: View) {
      val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString(),data.category.toString())
      Navigation.findNavController(view)
          .navigate(
              MainFragmentDirections.actionMainFragmentToViewPagerFragment(imageData)
              //                MainFragmentDirections.actionTestFragmentToDownloadFragment(
              //                    imageData
              //                )
          )
   }

   abstract fun initViewModel()
}