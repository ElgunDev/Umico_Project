package com.matrix.android105_android.domain.UseCase.likedProduct

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.domain.Network.FireBase.Repository.likedProduct.ILikedProductRepository
import javax.inject.Inject

class LikedProductUseCase @Inject constructor(
    private val likedProductRepository:ILikedProductRepository
):ILikedProductUseCase {
    override suspend fun likeProduct(productId: String) {
           likedProductRepository.addProduct(productId)
    }

    override suspend fun unLikeProduct(productId: String) {
        likedProductRepository.deleteProduct(productId)
    }

    override suspend fun isProductLiked(productId: String): Boolean {
        return likedProductRepository.isProductLiked(productId)
    }

    override suspend fun getLikedProducts(): List<Product> {
        return likedProductRepository.getLikedProducts()
    }


}