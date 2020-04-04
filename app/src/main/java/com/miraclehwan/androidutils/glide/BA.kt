package com.miraclehwan.androidutils.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BA {


    @JvmStatic
    @BindingAdapter("loadUrl")
    fun ImageView.loadUrl(urlString: String?) {
        urlString?.apply {
            Glide.with(context)
                .load(urlString)
                .into(this@loadUrl)
        }
    }

    @JvmStatic
    @BindingAdapter("loadDrawable")
    fun ImageView.loadDrawable(@RawRes @DrawableRes res: Int?) {
        res?.apply {
            Glide.with(context)
                .load(res)
                .into(this@loadDrawable)
        }
    }

    @JvmStatic
    @BindingAdapter("loadDrawable")
    fun ImageView.loadDrawable(drawable: Drawable?) {
        drawable?.apply {
            Glide.with(context)
                .load(drawable)
                .into(this@loadDrawable)
        }
    }
}