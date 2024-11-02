package com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products

import android.os.CountDownTimer

data class Product(
    val id:String,
    val credit:String,
    val category:String,
    val discountPrice:Double,
    val discountRate:String,
    val image:String,
    val name:String,
    val price:Double,
    val rating: String,
    val companyName:String,
    val companyLogo:String,
    var countDownTimer: CountDownTimer?=null,
    val stock:Long
)
