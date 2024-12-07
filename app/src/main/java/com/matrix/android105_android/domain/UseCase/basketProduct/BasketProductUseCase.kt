package com.matrix.android105_android.domain.UseCase.basketProduct

import androidx.lifecycle.MutableLiveData
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.data.Network.fireBase.Repository.basketProduct.BasketProductImplRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.basketProduct.IBasketProductRepository
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