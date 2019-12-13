package com.solidict.poc.vo.response_modals

data class Subscription(
    val appCount: Int,
    val callMinutes: Int,
    val dataGigabytes: Int,
    val digitalServiceMinutes: Int,
    val colorType: String,
    val id: String,
    val name: String,
    val periodType: String,
    val price: Double,
    val smsCount: Int
)