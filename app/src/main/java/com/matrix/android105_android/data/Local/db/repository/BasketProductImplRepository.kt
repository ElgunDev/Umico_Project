package com.matrix.android105_android.data.Local.db.repository

import androidx.lifecycle.LiveData
import com.matrix.android105_android.data.Local.db.dao.BasketProductDao
import com.matrix.android105_android.data.Local.db.entity.BasketProductEntity
import com.matrix.android105_android.domain.Local.Repository.BasketProduct.IBasketProductRepository
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

    override suspend fun isProductBasket(productId: String): Boolean {
        return  basketProductDao.isProductBasket(productId)
    }

    override suspend fun updateBasketProductQuantity(productId: String, newQuantity: Int) {
        basketProductDao.updateQuantity(productId,newQuantity)
    }

}