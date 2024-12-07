package com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products

import android.os.CountDownTimer

data class Product(
    val id:String="",
    val credit:String ="",
    val category:String="",
    val discountPrice:Double=0.0,
    val discountRate:String="",
    val image:String="",
    val name:String="",
    val price:Double=0.0,
    val rating: String="",
    val companyName:String="",
    val companyLogo:String="",
    var countDownTimer: CountDownTimer?=null,
    val stock:Long=0,
    var quantity:Int =1
){
    constructor() : this(
        id = "",
        credit = "",
        category = "",
        discountPrice = 0.0,
        discountRate = "",
        image = "",
        name = "",
        price = 0.0,
        rating = "",
        companyName = "",
        companyLogo = "",
        countDownTimer = null,
        stock = 0,
        quantity = 1
    )
}
