package com.miraclehwan.androidutils.shadow

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toRectF
import com.miraclehwan.androidutils.R

class ShadowView constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    companion object {
        const val colorBlack = "#b6b6b6"
        val Float.dp: Float
            get() = (this / Resources.getSystem().displayMetrics.density)
        val Float.px: Float
            get() = (this * Resources.getSystem().displayMetrics.density)
    }

    private var elevationValue = 0f
    private var cornerRoundValue = 0f
    private var elevationColor = colorBlack

    private lateinit var originPath: Path
    private lateinit var shadowPath: Path

    private val shadowPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.parseColor(elevationColor)
            maskFilter = BlurMaskFilter(elevationValue, BlurMaskFilter.Blur.NORMAL)
        }
    }

    init {
        attributeSet?.apply {
            val attr = context.obtainStyledAttributes(attributeSet, R.styleable.ShadowViewAttrs)
            elevationValue = attr.getFloat(R.styleable.ShadowViewAttrs_sv_elevation, 0f).px * 2
            if (attr.hasValue(R.styleable.ShadowViewAttrs_sv_elevation_color)) {
                elevationColor = attr.getString(R.styleable.ShadowViewAttrs_sv_elevation_color)!!
            }
            cornerRoundValue = attr.getFloat(R.styleable.ShadowViewAttrs_sv_corner_round, 0f).px
            attr.recycle()
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        if (elevationValue != 0f) {
            canvas?.apply {
                val originRect = clipBounds.toRectF()

                val inverseClipRect = RectF(
                    originRect.left,
                    originRect.top,
                    originRect.right,
                    originRect.bottom
                )

                originPath = Path().apply {
                    addRoundRect(
                        inverseClipRect,
                        cornerRoundValue,
                        cornerRoundValue,
                        Path.Direction.CW
                    )
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    clipOutPath(originPath)
                } else {
                    clipPath(originPath, Region.Op.DIFFERENCE)
                }

//                val shadowClipRect = RectF(
//                    originRect.left - originRect.left,
//                    originRect.top - 2f.px,
//                    originRect.right + originRect.right,
//                    originRect.bottom + originRect.bottom
//                )
//
//                shadowPath = Path().apply {
//                    addRect(shadowClipRect, Path.Direction.CCW)
//                }
//
//                clipPath(shadowPath)

                val shadowRect = RectF(
                    originRect.left + elevationValue / 4,
                    originRect.top + 8f.px,
                    originRect.right - elevationValue / 4,
                    originRect.bottom + elevationValue / 4
                )
                drawRoundRect(shadowRect, cornerRoundValue, cornerRoundValue, shadowPaint)
            }
        }
        super.onDraw(canvas)
    }
}