package com.matrix.android105_android.domain.Network.FireBase.Repository.likedProduct

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product

interface ILikedProductRepository {
    suspend fun addProduct(productId:String)
    suspend fun deleteProduct(productId: String)
    suspend fun isProductLiked(productId: String):Boolean

    suspend fun getLikedProducts():List<Product>
}