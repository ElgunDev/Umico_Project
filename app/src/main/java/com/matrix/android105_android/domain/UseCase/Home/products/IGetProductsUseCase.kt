package com.matrix.android105_android.domain.UseCase.Home.products

import com.matrix.android105_android.data.Repository.Home.Products.Product

interface IGetProductsUseCase {

    suspend fun getProducts():List<Product>
}