package com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product

interface IProductRepository {
    suspend fun getProduct():List<Product>
}