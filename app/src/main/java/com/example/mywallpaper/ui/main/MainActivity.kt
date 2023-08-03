package com.example.mywallpaper.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywallpaper.R

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setTheme(R.style.Theme_MyWallpaper)
      setContentView(R.layout.activity_main)
   }
}