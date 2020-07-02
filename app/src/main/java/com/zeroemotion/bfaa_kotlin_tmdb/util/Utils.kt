package com.zeroemotion.bfaa_kotlin_tmdb.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zeroemotion.bfaa_kotlin_tmdb.R

fun getProgressDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri:Int?, progressDrawable: CircularProgressDrawable){
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_error)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, urlString: Int?){
    view.loadImage(urlString, getProgressDrawable(view.context))
}