package com.matrix.android105_android.domain.UseCase.Home.products

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product

interface IGetAllProductUseCase {

    suspend fun getAllProducts(loadMore:Boolean = false , callback:(Result<List<Product>>) -> Unit)
}