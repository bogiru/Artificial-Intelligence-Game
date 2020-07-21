package com.example.artificialintelligencegame.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageDrawable")
fun imageDrawable(view: ImageView, img: Int) {
    Glide.with(view.context).load(img).into(view)
}