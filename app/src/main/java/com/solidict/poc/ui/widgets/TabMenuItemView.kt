package com.solidict.poc.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.solidict.poc.R

class TabMenuItemView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    val ivTabImage: ImageView
    val tvTabName: TextView


    init {
        inflate(context, R.layout.tab_menu_item, this)

        ivTabImage = findViewById(R.id.iv_tab_image)
        tvTabName = findViewById(R.id.tv_tab_name)

        // todo
//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TabMenuItemView)
//        imageView.setImageDrawable(attributes.getDrawable(R.styleable.BTabMenuItemView_image))
//        textView.text = attributes.getString(R.styleable.TabMenuItemView_text)
//        attributes.recycle()

    }
}