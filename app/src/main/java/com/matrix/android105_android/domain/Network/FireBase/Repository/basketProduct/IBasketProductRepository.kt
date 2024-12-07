package com.matrix.android105_android.domain.Network.FireBase.Repository.basketProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import kotlinx.coroutines.flow.Flow

interface IBasketProductRepository {
    suspend fun addProduct(productId:String)
    suspend fun deleteProduct(productId: String)
    suspend fun isProductBasket(productId: String):Boolean
    suspend fun getBasketProduct():List<Product>
}