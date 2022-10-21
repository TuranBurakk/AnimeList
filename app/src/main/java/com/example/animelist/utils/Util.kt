package com.example.animelist.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
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
fun showDialog(
    context: Context,
    title: String? = "Uyarı",
    message: String?,
    cancellable: Boolean = true,
    action: () -> Unit = {}
) {
    AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(cancellable)
        setPositiveButton("Tamam") { _, _ -> action.invoke() }
    }.show()
}


