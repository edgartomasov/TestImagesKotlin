package com.et.testimageskotlin.control

import com.et.testimageskotlin.models.ImgModel

interface ImgUrlListener {

    fun urlsComplete(urls: ArrayList<ImgModel>)
    fun error(e: Throwable)
}