package com.matrix.android105_android.domain.network.fireBase.repository.basketProduct

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface IBasketProductRepository {
    suspend fun addProduct(productId:String)
    suspend fun deleteProduct(productId: String)
    suspend fun isProductBasket(productId: String):Boolean
    suspend fun getBasketProduct():List<Product>
}