package com.matrix.android105_android.domain.useCase.home.products

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.domain.network.fireBase.repository.home.product.IProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
   private val productRepository: IProductRepository
):IGetProductsUseCase {
     override suspend fun getProducts(): List<Product> {
        return productRepository.getProduct()
     }
}