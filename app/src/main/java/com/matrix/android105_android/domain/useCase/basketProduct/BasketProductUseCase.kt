package com.matrix.android105_android.domain.useCase.basketProduct

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.domain.network.fireBase.repository.basketProduct.IBasketProductRepository
import javax.inject.Inject

class BasketProductUseCase @Inject constructor(
    private val basketProductImplRepository:IBasketProductRepository
):IBasketProductUseCase {
    override suspend fun basketProduct(productId: String) {
        basketProductImplRepository.addProduct(productId)
    }

    override suspend fun unBasketProduct(productId: String) {
        basketProductImplRepository.deleteProduct(productId)
    }

    override suspend fun isProductBasket(productId: String): Boolean {
        return basketProductImplRepository.isProductBasket(productId)
    }

    override suspend fun getBasketProduct(): List<Product> {
        return basketProductImplRepository.getBasketProduct()
    }
}