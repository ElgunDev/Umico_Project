package com.matrix.android105_android.domain.UseCase.likedProduct

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product

interface ILikedProductUseCase {
    suspend fun likeProduct(productId:String)
    suspend fun unLikeProduct(productId: String)

    suspend fun isProductLiked(productId: String):Boolean
    suspend fun getLikedProducts():List<Product>

}