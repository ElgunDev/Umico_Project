package com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products

import android.os.CountDownTimer

data class Product(
    val id:String,
    val credit:String,
    val category:String,
    val discountPrice:String,
    val discountRate:String,
    val image:String,
    val name:String,
    val price:String,
    val rating: String,
    var countDownTimer: CountDownTimer?=null
)
