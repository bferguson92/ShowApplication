package com.example.showapplication.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.showapplication.util.ImageDownloader
import com.example.showapplication.R
import com.example.showapplication.util.Constants
import kotlinx.android.synthetic.main.image_fragment_layout.*

class ImageFragment(val relation: String): Fragment(), ImageDownloader.ImageListener {
    private lateinit var imageDownloader: ImageDownloader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageDownloader = ImageDownloader(this)
        when(relation){
            "SHAPER" -> imageDownloader.execute(Constants.IMAGE_SHAPER)
            "CRIMINAL" -> imageDownloader.execute(Constants.IMAGE_CRIMINAL)
            "ANARCH" -> imageDownloader.execute(Constants.IMAGE_ANARCH)
        }
        Log.e("Tag_FRAGMENT", relation)
    }

    override fun updateImage(bitmap: Bitmap?) {
        Log.e("TAG_BITMAP", "BITMAP SET")
        image_view_selected.setImageBitmap(bitmap)
    }

}