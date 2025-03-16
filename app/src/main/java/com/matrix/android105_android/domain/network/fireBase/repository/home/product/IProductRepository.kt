package com.matrix.android105_android.domain.network.fireBase.repository.home.product

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface IProductRepository {
    suspend fun getProduct():List<Product>

    suspend fun updateProductStock(productId:String , newStock:Long)
}