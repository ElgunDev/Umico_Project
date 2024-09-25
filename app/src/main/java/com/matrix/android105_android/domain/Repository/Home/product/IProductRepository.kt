package com.matrix.android105_android.domain.Repository.Home.product

import com.matrix.android105_android.data.Repository.Products.Product

interface IProductRepository {
    suspend fun getProduct():List<Product>
}