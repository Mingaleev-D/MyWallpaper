package com.example.mywallpaper.ui.adapter.recyclerview

import android.view.View
import com.example.mywallpaper.data.remote.model.Category

/**
 * @author : Mingaleev.D
 * @data : 03.08.2023
 */

interface CategoryInteractionListener {
   fun onClickCategory(category: Category, view: View)
}