package com.matrix.android105_android.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("basket_product")
data class BasketProductEntity(
    @PrimaryKey
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
    val stock:Long,
    var quantity: Int = 1

)
