package com.example.mywallpaper.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mywallpaper.R
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.databinding.FragmentSearchBinding
import com.example.mywallpaper.ui.adapter.loadingState.LoadStateAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.RecyclerViewAdapter
import com.example.mywallpaper.ui.adapter.recyclerview.WallInteractionListener
import com.example.mywallpaper.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), WallInteractionListener {

   val viewModel: SearchViewModel by viewModels()
   var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter( this)
   private lateinit var binding: FragmentSearchBinding

   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View  {
      binding = FragmentSearchBinding.inflate(inflater)
      initViewModel()
      recyclerAdapter()
      return binding.root
   }

   @SuppressLint("SuspiciousIndentation")
   fun initViewModel() {

      binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
         if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            viewModel.searchFromApi(binding.searchEditText.text.toString())

            val imm =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)

         }
         true
      }

      viewModel.data.observe(requireActivity()) {
         lifecycleScope.launch {
            recyclerViewAdapter.submitData(it)
         }
      }
   }

   private fun recyclerAdapter() {
      val layoutManager = GridLayoutManager(context, 3)
      binding.searchRecyclerView.layoutManager = layoutManager
      binding.searchRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
          header = LoadStateAdapter { recyclerViewAdapter.retry() },
          footer = LoadStateAdapter { recyclerViewAdapter.retry() }
      )
      recyclerViewAdapter.addLoadStateListener { loadState ->
         binding.searchRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
         binding.searchProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
         handelError(loadState )
      }
   }




   override fun onClickItem(data: Data, view: View) {
      val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
      Navigation.findNavController(view)
          .navigate(
              SearchFragmentDirections.actionSearchFragmentToDownloadFragment(
                  imageData
              )
          )
   }

   private fun handelError(loadStates: CombinedLoadStates) {
      val errorState = loadStates.source.append as? LoadState.Error
                       ?: loadStates.source.prepend as? LoadState.Error
      errorState?.let {
         Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
      }
   }


}