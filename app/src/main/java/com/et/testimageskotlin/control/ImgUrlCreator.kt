package com.et.testimageskotlin.control

import com.et.testimageskotlin.api.NetworkApi
import com.et.testimageskotlin.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImgUrlCreator(imgUrlListener: ImgUrlListener) {

    private val page = 2
    private val offset = 100

    private var imgUrlListener: ImgUrlListener ? = imgUrlListener
    private val service: NetworkApi get() = RetrofitClient.createClient(NetworkApi::class.java)
    init {
        createUrls()
    }

    private fun createUrls(){

        service.getImages(page, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    imgUrlListener!!.urlsComplete(it)
                },{
                    imgUrlListener!!.error(it)
                }
            )

    }
}