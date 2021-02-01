package com.et.testimageskotlin

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.et.testimageskotlin.adapters.ImgMainAdapter
import com.et.testimageskotlin.control.ImgUrlCreator
import com.et.testimageskotlin.control.ImgUrlListener
import com.et.testimageskotlin.models.ImgModel
import com.et.testimageskotlin.ui.dialogs.ImageDialog

class MainPresenter(private val context: Context, private val img_recycler: RecyclerView): ImgUrlListener {

    init {
        createUrlList()
    }

    private fun createUrlList(){
        ImgUrlCreator(this)
    }

    override fun urlsComplete(images: ArrayList<ImgModel>) {
        val imgMainAdapter = ImgMainAdapter(images)
        img_recycler.adapter = imgMainAdapter
        img_recycler.layoutManager = GridLayoutManager(context, 2)
        imgMainAdapter.onImgClick = {
            openImg(it)
        }
    }

    override fun error(e: Throwable) {
        Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun openImg(imgModel: ImgModel){
        val dialog = ImageDialog(context, imgModel)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

}