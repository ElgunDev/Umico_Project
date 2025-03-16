package com.matrix.android105_android.domain.network.fireBase.repository.likedProduct

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface ILikedProductRepository {
    suspend fun addProduct(productId:String)
    suspend fun deleteProduct(productId: String)
    suspend fun isProductLiked(productId: String):Boolean

    suspend fun getLikedProducts():List<Product>
}