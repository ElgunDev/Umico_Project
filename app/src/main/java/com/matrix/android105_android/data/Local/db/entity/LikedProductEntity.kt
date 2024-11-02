package com.matrix.android105_android.data.Local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_products")
data class LikedProductEntity(
    @PrimaryKey
    val id:String,
    val credit:String,
    val category:String,
    val discountPrice:Double,
    val discountRate:String,
    val image:String,
    val name:String,
    val price:Double,
    val rating: String
)
