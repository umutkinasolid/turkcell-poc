package com.solidict.poc.ui.widgets

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import com.solidict.poc.R

class CustomBottomBarItemSpecial : CustomBottomBarItem {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun customizeAtInit() {
        super.customizeAtInit()
        val homeButtonSize = resources.getDimension(R.dimen.home_button_size).toInt()
        val homeButtonPadding = resources.getDimension(R.dimen.home_button_padding).toInt()

        iconImage.layoutParams = LayoutParams(homeButtonSize, homeButtonSize)
        iconImage.setPadding(
            homeButtonPadding,
            homeButtonPadding,
            homeButtonPadding,
            homeButtonPadding
        )
        iconImage.setBackgroundResource(R.drawable.home_icon)
    }

    override fun setSelectedState() {
        tabTitle.setTextColor(Color.BLACK)

    }

    override fun setUnselectedState() {
        tabTitle.setTextColor(Color.WHITE)

    }
}