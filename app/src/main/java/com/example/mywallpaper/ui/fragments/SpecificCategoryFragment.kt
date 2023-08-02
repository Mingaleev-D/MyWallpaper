package com.example.mywallpaper.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaper.R
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.databinding.FragmentSpecificCategoryBinding
import com.example.mywallpaper.ui.adapter.loadingState.LoadStateAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.WallInteractionListener
import com.example.mywallpaper.ui.viewmodel.CategoriesViewModel
import com.example.mywallpaper.ui.viewmodel.CategoriesViewModelFactory
import kotlinx.coroutines.launch


class SpecificCategoryFragment : Fragment(), WallInteractionListener {

   private lateinit var viewModel: CategoriesViewModel
   private val args: SpecificCategoryFragmentArgs by navArgs()
   private lateinit var binding: FragmentSpecificCategoryBinding
   private var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View {
      binding = FragmentSpecificCategoryBinding.inflate(inflater)

      initViewModel()
      recyclerAdapter()
      categoryName()
      callBack()

      return binding.root
   }

   private fun initViewModel() {
      viewModel =
          ViewModelProvider(
              this,
              CategoriesViewModelFactory(args.categoryName)
          )[CategoriesViewModel::class.java]

      viewModel.data.observe(viewLifecycleOwner) {
         lifecycleScope.launch {
            recyclerViewAdapter.submitData(it)
         }
      }
   }

   private fun recyclerAdapter() {
      val layoutManager = GridLayoutManager(context, 3)
      binding.wallCategoriesRecyclerView.layoutManager = layoutManager
      binding.wallCategoriesRecyclerView.adapter =
          recyclerViewAdapter.withLoadStateHeaderAndFooter(
              header = LoadStateAdapter { recyclerViewAdapter.retry() },
              footer = LoadStateAdapter { recyclerViewAdapter.retry() }
          )


      recyclerViewAdapter.addLoadStateListener { loadState ->
         binding.wallCategoriesRecyclerView.isVisible =
             loadState.source.refresh is LoadState.NotLoading
         binding.CategoryProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
         binding.CategoryButtonRetry.isVisible = loadState.source.refresh is LoadState.Error
         handelError(loadState)
      }
      binding.CategoryButtonRetry.setOnClickListener {
         recyclerViewAdapter.retry()
      }

   }

   private fun handelError(loadStates: CombinedLoadStates) {
      val errorState = loadStates.source.append as? LoadState.Error
                       ?: loadStates.source.prepend as? LoadState.Error

      errorState?.let {
         Toast.makeText(context, "please repeat again", Toast.LENGTH_LONG).show()
      }
   }

   private fun categoryName() {
      binding.categoryName.text = args.categoryName
   }

   override fun onClickItem(data: Data, view: View) {
      val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
      Navigation.findNavController(view)
          .navigate(
              SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToDownloadFragment(
                  imageData
              )
          )
   }

   private fun callBack() {
      binding.categoryName.onRightDrawableClicked {
         Navigation.findNavController(it).popBackStack()
      }

   }

   @SuppressLint("ClickableViewAccessibility")
   private fun TextView.onRightDrawableClicked(onClicked: (view: TextView) -> Unit) {
      this.setOnTouchListener { v, event ->
         var hasConsumed = false
         if (v is TextView) {
            if (event.x >= v.width - v.totalPaddingRight) {
               if (event.action == MotionEvent.ACTION_UP) {
                  onClicked(this)
               }
               hasConsumed = true
            }
         }
         hasConsumed
      }
   }
}
