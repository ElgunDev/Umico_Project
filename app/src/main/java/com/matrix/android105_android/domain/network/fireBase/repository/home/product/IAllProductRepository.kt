package com.matrix.android105_android.domain.network.fireBase.repository.home.product

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface IAllProductRepository{

    suspend fun getAllProducts(loadMore:Boolean = false , callback:(Result<List<Product>>) -> Unit)
}