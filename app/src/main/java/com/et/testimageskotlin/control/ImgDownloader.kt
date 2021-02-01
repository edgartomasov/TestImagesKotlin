package com.et.testimageskotlin.control

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.widget.ImageView
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class ImgDownloader(context: Context, img: ImageView?, url: String):
    AsyncTask<String, Void, Bitmap>() {

    private val context = context
    private val img = img

    init {
        execute(url)
    }

    override fun doInBackground(vararg urls: String): Bitmap? {
        val imgUrl = urls[0]
        var bitmap: Bitmap ?= null

        try {
            val stream = URL(imgUrl).openStream()
            bitmap = BitmapFactory.decodeStream(stream)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap

        //return null
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if (result != null){
            img?.setImageBitmap(result)
        } else {
            // Nothing
        }
    }

    private fun downloadImg2(imgUrl: String): Bitmap? {
        val connection = URL(imgUrl).openConnection()
        connection.connect()
        val stream = connection.getInputStream()
        val buffer = BufferedInputStream(stream)
        val bitmap = BitmapFactory.decodeStream(buffer)
        stream.close()
        buffer.close()
        return bitmap
    }

    private fun downloadImg(imgUrl: String): Bitmap? {
        val bitmap: Bitmap
        val stream: InputStream ?
        val bmOptions = BitmapFactory.Options()
        bmOptions.inSampleSize = 1

        stream = getHttpConnection(imgUrl)

        if (stream != null){
            bitmap = BitmapFactory.decodeStream(stream, null, bmOptions)!!
            stream.close()
            return bitmap
        }

        return null
    }

    private fun getHttpConnection(imgUrl: String): InputStream? {
        try {
            val url = URL(imgUrl)
            val connection = url.openConnection()
            val httpConnection = connection as HttpURLConnection
            httpConnection.requestMethod = "GET"
            httpConnection.connect()

            if (httpConnection.responseCode == HttpURLConnection.HTTP_OK){
                return httpConnection.inputStream
            }
        } catch (e: IOException){
            e.printStackTrace()
        }

        return null
    }
}
