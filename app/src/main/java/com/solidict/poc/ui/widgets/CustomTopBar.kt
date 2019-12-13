package com.solidict.poc.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.solidict.poc.R

class CustomTopBar : FrameLayout {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private var arcH = 0f
    private var circleR = 0f
    private var circleCenterP = PointF()
    private val circlePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.WHITE
    }


    private fun init(attrs: AttributeSet?) {
        //circlePaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTopBar,
            0, 0
        ).apply {
            try {
                arcH = getDimension(R.styleable.CustomTopBar_arcHeight, 6f)
            } finally {
                recycle()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleR = (w * w / (8 * arcH)) + arcH / 2
        circleCenterP.x = w / 2f
        circleCenterP.y = h + circleR - arcH
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.run {
            this.save()
            this.clipRect(this.clipBounds)
            this.drawCircle(circleCenterP.x, circleCenterP.y, circleR, circlePaint)
            this.restore()
        }
        super.dispatchDraw(canvas)
    }
}