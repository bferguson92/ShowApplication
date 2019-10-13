package com.example.showapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.AsyncTask
import android.util.Log
import java.net.URL

class ImageDownloader(val imageListener: ImageListener): AsyncTask<String?, Int, Bitmap?>(){

    interface ImageListener{
        fun updateImage(bitmap: Bitmap?)
    }



    override fun doInBackground(vararg params: String?): Bitmap? {
        val urlStream = URL(params[0]).openConnection().getInputStream()

        var bitmap:Bitmap? = null

        try{
            bitmap = BitmapFactory.decodeStream(urlStream)
        } catch (throwable: Throwable){
            Log.e("TAG_ERROR", throwable.toString())
        }

        Log.e("TAG_BITMAP", "JOB'S DONE")
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageListener.updateImage(result)
    }
}