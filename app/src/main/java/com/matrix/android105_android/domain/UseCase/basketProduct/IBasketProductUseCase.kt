package com.matrix.android105_android.domain.UseCase.basketProduct

import androidx.lifecycle.MutableLiveData
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product

interface IBasketProductUseCase {

    suspend fun basketProduct(productId:String)
    suspend fun unBasketProduct(productId: String)
    suspend fun isProductBasket(productId: String):Boolean
    suspend fun getBasketProduct():List<Product>
}