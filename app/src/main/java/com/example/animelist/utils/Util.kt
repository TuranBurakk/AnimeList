package com.example.animelist.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.animelist.R

fun  ImageView.downloadFromUrl(url: String?){

    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}


