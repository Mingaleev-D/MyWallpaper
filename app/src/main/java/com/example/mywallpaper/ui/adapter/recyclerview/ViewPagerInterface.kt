package com.example.mywallpaper.ui.adapter.recyclerview

import android.view.View
import com.example.mywallpaper.data.remote.model.Data
import com.example.mywallpaper.databinding.ItemViewpagerBinding

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

interface ViewPagerInterface{
   fun positionItem(data: Data, position:Int)
   fun onClickCategory(data : Data, view: View, binding: ItemViewpagerBinding)
}