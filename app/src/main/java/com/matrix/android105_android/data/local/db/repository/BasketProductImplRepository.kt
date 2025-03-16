package com.matrix.android105_android.data.local.db.repository

import androidx.lifecycle.LiveData
import com.matrix.android105_android.data.local.db.dao.BasketProductDao
import com.matrix.android105_android.data.local.db.entity.BasketProductEntity
import com.matrix.android105_android.domain.local.repository.basketProduct.IBasketProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketProductImplRepository @Inject constructor(
    private val basketProductDao: BasketProductDao
):IBasketProductRepository {
    override suspend fun addProductToDatabase(basketProductEntity: BasketProductEntity) {
       basketProductDao.insertProduct(basketProductEntity)
    }

    override fun getBasketProduct(): LiveData<List<BasketProductEntity>> {
        return basketProductDao.getBasketProduct()
    }

    override suspend fun deleteBasketProduct(productId: String) {
        basketProductDao.deleteProduct(productId)
    }

    override suspend fun isProductBasket(productId: String): Flow<Boolean> {
        return  basketProductDao.isProductBasket(productId)
    }

    override suspend fun updateBasketProductQuantity(productId: String, newQuantity: Int) {
        basketProductDao.updateQuantity(productId,newQuantity)
    }

}