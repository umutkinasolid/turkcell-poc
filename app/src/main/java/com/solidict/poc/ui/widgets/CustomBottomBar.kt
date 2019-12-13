package com.solidict.poc.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.solidict.poc.R
import com.solidict.poc.vo.response_modals.NavBarItem

class CustomBottomBar : ConstraintLayout {
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

    private var arcDif = 0f
    private var arcPaddingTop = 0f
    private var circleR = 0f
    private var circleCenterP = PointF()
    private val circlePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL

    }

    private val childList = arrayListOf<CustomBottomBarItem>()
    private var itemSelectionListener: onTabItemSelectedListener? = null

    private var currentSelectedItem: CustomBottomBarItem? = null

    private fun init(attrs: AttributeSet?) {

        circlePaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomBottomBar,
            0, 0
        ).apply {
            try {
                arcDif = getDimension(R.styleable.CustomBottomBar_arcHeightDif, 6f)
                arcPaddingTop = getDimension(R.styleable.CustomBottomBar_arcPaddingTop, 0f)
            } finally {
                recycle()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circleR = (w * w / (8 * arcDif)) + arcDif / 2
        circleCenterP.x = w / 2f
        circleCenterP.y = arcPaddingTop + circleR
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas ?: return
        canvas.run {
            this.save()
            this.clipRect(this.clipBounds)
            this.drawCircle(circleCenterP.x, circleCenterP.y, circleR, circlePaint)
            this.restore()
        }
        super.dispatchDraw(canvas)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        childList.clear()
        for (i in 0 until childCount) {
            childList.add(getChildAt(i) as CustomBottomBarItem)
            getChildAt(i).setOnClickListener { view -> onItemSelected(view as CustomBottomBarItem) }
        }
    }


    private fun onItemSelected(item: CustomBottomBarItem) {
        if (item === currentSelectedItem) return
        currentSelectedItem?.setUnselectedState()
        currentSelectedItem = item
        currentSelectedItem!!.setSelectedState()
        itemSelectionListener?.onTabItemSelected(currentSelectedItem!!.id)

    }

    fun setTabSelectedListener(listener: onTabItemSelectedListener) {
        itemSelectionListener = listener
    }


    public interface onTabItemSelectedListener {
        fun onTabItemSelected(viewId: Int)
    }


}