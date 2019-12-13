package com.solidict.poc.ui.widgets

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import android.util.TypedValue
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.solidict.poc.R
import kotlinx.android.synthetic.main.tab_menu_item.view.*
import org.w3c.dom.Text


open class CustomBottomBarItem : LinearLayout {

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    lateinit var iconImage: ImageView

    lateinit var tabTitle: TextView

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.tab_menu_item, this, true)
        gravity = Gravity.CENTER_HORIZONTAL
        orientation = VERTICAL
        val outValue = TypedValue()
        context.theme.resolveAttribute(R.attr.selectableItemBackgroundBorderless, outValue, true)
        this.setBackgroundResource(outValue.resourceId)

        iconImage = iv_tab_image
        tabTitle = tv_tab_name
        customizeAtInit()
    }

    open fun customizeAtInit(){

    }


    fun setButtonTitleAndIcon(buttonTitle: String, buttonIconUrl: String) {
        Glide.with(this)
            .load(buttonIconUrl)
            .into(iconImage)

        tabTitle.text = buttonTitle
    }

    open fun setSelectedState() {
        iconImage.setColorFilter(Color.BLACK)
        tabTitle.setTextColor(Color.BLACK)
    }

    open fun setUnselectedState() {
        iconImage.setColorFilter(Color.WHITE)
        tabTitle.setTextColor(Color.WHITE)
    }

    fun getIconImageView(): ImageView {
        return iconImage
    }

    fun getItemTextView(): TextView {
        return tabTitle
    }


}