package com.matrix.android105_android.domain.useCase.likedProduct

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface ILikedProductUseCase {
    suspend fun likeProduct(productId:String)
    suspend fun unLikeProduct(productId: String)

    suspend fun isProductLiked(productId: String):Boolean
    suspend fun getLikedProducts():List<Product>

}