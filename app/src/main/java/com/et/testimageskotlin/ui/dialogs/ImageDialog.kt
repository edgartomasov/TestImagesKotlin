package com.et.testimageskotlin.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.et.testimageskotlin.R
import com.et.testimageskotlin.control.ImgDownloader
import com.et.testimageskotlin.models.ImgModel

class ImageDialog(context: Context,
                  private val imgModel: ImgModel) : Dialog(context) {

    // TEST 1

    private lateinit var main_cl: ConstraintLayout
    private lateinit var title: TextView
    private lateinit var dialog_img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_image)

        main_cl = findViewById(R.id.main_cl)
        title = findViewById(R.id.title)
        dialog_img = findViewById(R.id.dialog_img)

        main_cl.layoutParams.width = context.resources.displayMetrics.widthPixels

        main_cl.layoutParams.height = (context.resources.displayMetrics.heightPixels
         - (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32f, context.resources.displayMetrics)).toInt())

        initImages()
    }

    private fun initImages(){
        ImgDownloader(context, dialog_img, imgModel.download_url)
        title.text = imgModel.download_url
    }
}