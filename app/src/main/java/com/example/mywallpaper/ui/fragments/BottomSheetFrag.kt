package com.example.mywallpaper.ui.fragments

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mywallpaper.R
import com.example.mywallpaper.data.common.Constants
import com.example.mywallpaper.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import java.io.IOException

/**
 * @author : Mingaleev.D
 * @data : 02.08.2023
 */

class BottomSheetFrag(private val wallUrl :String) : BottomSheetDialogFragment() {

   private lateinit var binding: BottomSheetBinding

   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View {
      binding = BottomSheetBinding.inflate(inflater, container, false)

      initButtons()
      return binding.root
   }

   private fun initButtons() {


      binding.downLoadFromNet.setOnClickListener {
         downloadImageFromWeb(wallUrl)
      }
      binding.setAsBackground.setOnClickListener {
         setAsBackground(Constants.BackGroundState.backGround)
      }
      binding.setAsLockscreen.setOnClickListener {
         setAsBackground(Constants.BackGroundState.lockScreen)
      }
   }


   private fun downloadImageFromWeb(url: String) {
      try {
         val downloadManager =
             context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

         val downloadUri = Uri.parse(url)

         val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setMimeType("image/*")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("wool")
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + "wool" + ".jpg",
                )

         }
         downloadManager.enqueue(request)
         Toast.makeText(context, getString(R.string.downloading), Toast.LENGTH_LONG).show()

      } catch (e: java.lang.Exception) {
         Toast.makeText(context,
                        getString(R.string.image_download_failed, e.message), Toast.LENGTH_LONG).show()
      }

   }


   private fun setAsBackground(LockOrBackGround: Int) {

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            val image = activity?.findViewById<ShapeableImageView>(R.id.download_image_view)

            if (image?.drawable == null) {
               Toast.makeText(context, getString(R.string.wait_to_loading), Toast.LENGTH_LONG).show()
            } else {
               val bitmap = (image.drawable as BitmapDrawable).bitmap
               wallpaperManager.setBitmap(bitmap, null, true, LockOrBackGround)
               Toast.makeText(context, getString(R.string.done), Toast.LENGTH_LONG).show()
            }

         } catch (e: IOException) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
         }
      }
   }

}