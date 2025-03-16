package com.matrix.android105_android.domain.useCase.basketProduct

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface IBasketProductUseCase {

    suspend fun basketProduct(productId:String)
    suspend fun unBasketProduct(productId: String)
    suspend fun isProductBasket(productId: String):Boolean
    suspend fun getBasketProduct():List<Product>
}