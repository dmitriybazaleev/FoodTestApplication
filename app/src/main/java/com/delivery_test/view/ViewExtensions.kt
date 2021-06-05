package com.delivery_test.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.delivery_test.R


fun TextView.setTextOrHide(text: String?) {
    if (text == null || text.isEmpty()) {
        this.visibility = View.GONE
    } else {
        this.text = text
    }
}

fun ImageView.loadImageByUrl(url: String?) {
    if (url != null && url != "") {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.icn_food_place_holder)
            .into(this)
    }
}