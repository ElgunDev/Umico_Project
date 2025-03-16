package com.matrix.android105_android.domain.useCase.likedProduct

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.domain.network.fireBase.repository.likedProduct.ILikedProductRepository
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