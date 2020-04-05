package com.miraclehwan.androidutils.utils.glide.transformation

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * 2019.04.05 miraclehwan
 *
 * it only support CornerType.ALL.
 * i will update code when i use other CornerType.
 */
class RoundedCornersStroke @JvmOverloads constructor(
    private val radius: Int,
    private val margin: Int = 1,
    private val cornerType: CornerType = CornerType.ALL,
    private val strokeColor: Int = Color.RED,
    private val strokeWidth: Float = 1f
) : RoundedCornersTransformation(radius, margin, cornerType) {

    val strokePaint = Paint().apply {
        color = strokeColor
        strokeWidth = this@RoundedCornersStroke.strokeWidth
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun transform(
        context: Context,
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val roundedBitmap = super.transform(context, pool, toTransform, outWidth, outHeight)
        // TODO :: support other cornerType
        Canvas(roundedBitmap).apply {
            drawRoundRect(
                RectF(
                    margin.toFloat(),
                    margin.toFloat(),
                    (toTransform.width - margin).toFloat(),
                    (toTransform.height - margin).toFloat()
                ),
                radius.toFloat(),
                radius.toFloat(),
                strokePaint
            )
        }
        return roundedBitmap
    }
}