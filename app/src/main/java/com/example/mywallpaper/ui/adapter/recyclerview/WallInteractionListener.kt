package com.example.mywallpaper.ui.adapter.recyclerview

import android.view.View
import com.example.mywallpaper.data.remote.model.Data

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

interface WallInteractionListener {
   fun onClickItem(data : Data, view: View)
}