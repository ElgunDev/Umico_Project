package com.matrix.android105_android.domain.local.repository.basketProduct

import androidx.lifecycle.LiveData
import com.matrix.android105_android.data.local.db.entity.BasketProductEntity
import kotlinx.coroutines.flow.Flow

interface IBasketProductRepository {
    suspend fun addProductToDatabase(basketProductEntity: BasketProductEntity)

    fun getBasketProduct():LiveData<List<BasketProductEntity>>

    suspend fun deleteBasketProduct(productId:String)

    suspend fun isProductBasket(productId: String):Flow<Boolean>
    suspend fun updateBasketProductQuantity(productId: String , newQuantity:Int)


}