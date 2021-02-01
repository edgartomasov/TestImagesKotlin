package com.et.testimageskotlin.adapters

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.et.testimageskotlin.R
import com.et.testimageskotlin.control.ImgDownloader
import com.et.testimageskotlin.models.ImgModel

class ImgMainAdapter(images: ArrayList<ImgModel>): RecyclerView.Adapter<ImgMainAdapter.ImgMainHolder>() {

    private val images = images
    var onImgClick: ((ImgModel) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgMainHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImgMainHolder(inflater.inflate(R.layout.item_img_main, parent, false))
    }

    override fun onBindViewHolder(holder: ImgMainHolder, position: Int) {
        val imgModel = images[position]

        val lp = holder.img?.layoutParams as RecyclerView.LayoutParams
        lp.height = ((holder.context.resources.displayMetrics.widthPixels / 2)
                - (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            8f,
            holder.context.resources.displayMetrics)).toInt())

        ImgDownloader(holder.context, holder.img, imgModel.download_url)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImgMainHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val context: Context
            get() = itemView.context

        var img: ImageView? = null

        init {
            img = itemView.findViewById(R.id.img)
            itemView.setOnClickListener {
                onImgClick?.invoke(images[adapterPosition])
            }
        }
    }
}