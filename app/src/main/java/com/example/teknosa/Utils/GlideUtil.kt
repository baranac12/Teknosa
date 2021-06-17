package com.example.teknosa.Utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.teknosa.R
//internetten resim çekmemize yarıyor
class GlideUtil {
    fun resimleriGoster(context: Context,url: String?, img: ImageView) {
        Glide.with(context)
            .load(url)
            .fitCenter()
            .into(img)
    }
}


