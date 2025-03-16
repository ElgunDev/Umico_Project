package com.matrix.android105_android.domain.useCase.home.products

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product

interface IGetAllProductUseCase {

    suspend fun getAllProducts(loadMore:Boolean = false , callback:(Result<List<Product>>) -> Unit)
}