package com.et.testimageskotlin

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var img_recycler: RecyclerView
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_recycler = findViewById(R.id.img_recycler)

        initPresenter()
    }

    private fun initPresenter(){
        presenter = MainPresenter(this, img_recycler);
    }

}