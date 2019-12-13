package com.solidict.poc.ui.package_activity.data_binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.solidict.poc.R

@BindingAdapter("subscriptionBgButtonBinding")
fun SubscriptionBgButton(view: View, colorType: String) {
    when (colorType) {
        "PINK" -> view.setBackgroundResource(R.drawable.pembe_buton)
        "ORANGE" -> view.setBackgroundResource(R.drawable.turuncu_buton)
        "PURPLE" -> view.setBackgroundResource(R.drawable.mor_buton)
    }
}

@BindingAdapter("subscriptionSrcItemBinding")
fun SubscriptionSrcItem(view: ImageView, colorType: String) {
    when (colorType) {
        "PINK" -> view.setImageResource(R.drawable.pembe_dalga)
        "ORANGE" -> view.setImageResource(R.drawable.turuncu_dalga)
        "PURPLE" -> view.setImageResource(R.drawable.mor_dalga)
    }
}