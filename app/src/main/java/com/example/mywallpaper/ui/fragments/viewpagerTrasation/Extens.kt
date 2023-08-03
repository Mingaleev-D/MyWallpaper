package com.example.mywallpaper.ui.fragments.viewpagerTrasation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */
@BindingAdapter("android:src")
fun bindImageView(view: ImageView, url: String?) {
   Glide.with(view).load(url).centerCrop().into(view)
}