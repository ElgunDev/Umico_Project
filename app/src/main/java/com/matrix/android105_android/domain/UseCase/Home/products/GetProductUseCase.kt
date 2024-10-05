package com.matrix.android105_android.domain.UseCase.Home.products

import com.matrix.android105_android.data.Repository.Home.Products.Product
import com.matrix.android105_android.domain.Repository.Home.product.IProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
   private val productRepository:IProductRepository
):IGetProductsUseCase {
     override suspend fun getProducts(): List<Product> {
        return productRepository.getProduct()
     }
}