package com.miraclehwan.androidutils.utils.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.miraclehwan.androidutils.utils.glide.transformation.RoundedCornersStroke

object BA {

    /**
     * if you use CenterCrop Option, you must call centerCrop() previous transformations
     *
     * example)
     * -> RequestOptions().transform(CenterCrop(), do transformations...)
     */

    @JvmStatic
    @BindingAdapter("loadUrl", "cornerRound", requireAll = false)
    fun ImageView.loadUrl(urlString: String?, roundingRadius: Int = 0) {
        urlString?.apply {
            val requestBuilder = Glide.with(context)
                .load(urlString)
            if (roundingRadius > 0) {
                requestBuilder.apply(
                    RequestOptions()
                        .transform(RoundedCorners(roundingRadius))
                )
            }
            requestBuilder.into(this@loadUrl)
        }
    }

    @JvmStatic
    @BindingAdapter("loadDrawable", "cornerRound", requireAll = false)
    fun ImageView.loadDrawable(@RawRes @DrawableRes res: Int?, roundingRadius: Int = 0) {
        res?.apply {
            val requestBuilder = Glide.with(context)
                .load(res)
            if (roundingRadius > 0) {
                requestBuilder.apply(
                    RequestOptions()
                        .transform(RoundedCorners(roundingRadius))
                )
            }
            requestBuilder.into(this@loadDrawable)
        }
    }

    @JvmStatic
    @BindingAdapter("loadDrawable", "cornerRound", requireAll = false)
    fun ImageView.loadDrawable(drawable: Drawable?, roundingRadius: Int = 0) {
        drawable?.apply {
            val requestBuilder = Glide.with(context)
                .load(drawable)
            if (roundingRadius > 0) {
                requestBuilder.apply(
                    RequestOptions()
                        .transform(RoundedCorners(roundingRadius))
                )
            }
            requestBuilder.into(this@loadDrawable)
        }
    }

    @JvmStatic
    @BindingAdapter("loadDrawable", "cornerRound", "useStroke", "strokeWidth", requireAll = true)
    fun ImageView.loadDrawable(
        drawable: Drawable?,
        roundingRadius: Int = 0,
        useStroke: Boolean = false,
        strokeWidth: Float = 1f
    ) {
        drawable?.apply {
            val requestBuilder = Glide.with(context)
                .load(drawable)
            if (roundingRadius > 0) {
                requestBuilder.apply(
                    if (useStroke) {
                        RequestOptions()
                            .transform(CenterCrop(), RoundedCornersStroke(roundingRadius, strokeWidth = strokeWidth))
                    } else {
                        RequestOptions()
                            .transform(CenterCrop(), RoundedCorners(roundingRadius))
                    }
                )
            }
            requestBuilder.into(this@loadDrawable)
        }
    }
}