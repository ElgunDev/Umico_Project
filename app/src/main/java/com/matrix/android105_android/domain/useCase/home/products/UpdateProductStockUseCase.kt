package com.matrix.android105_android.domain.useCase.home.products

import com.matrix.android105_android.domain.network.fireBase.repository.home.product.IProductRepository
import javax.inject.Inject

class UpdateProductStockUseCase @Inject constructor(
    private val productRepository:IProductRepository
) {
    suspend fun increaseStock(productId: String, currentStock: Long): Long {
        val newStock = currentStock - 1
        if (newStock>=0){
            productRepository.updateProductStock(productId , newStock)
        }
        return newStock
    }

   suspend fun decreaseStock(productId: String, currentStock: Long): Long {
        val newStock = currentStock +1
        productRepository.updateProductStock(productId ,newStock)
        return newStock
    }

}