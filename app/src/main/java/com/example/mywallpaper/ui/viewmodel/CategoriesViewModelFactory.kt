package com.example.mywallpaper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

class CategoriesViewModelFactory(private val categoryID: String) : ViewModelProvider.Factory {

   override fun <T : ViewModel> create(modelClass: Class<T>): T {

      if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
         return CategoriesViewModel(categoryID) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class")
   }
}