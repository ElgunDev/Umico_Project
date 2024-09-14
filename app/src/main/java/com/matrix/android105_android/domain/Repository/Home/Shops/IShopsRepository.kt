package com.matrix.android105_android.domain.Repository.Home.Shops

import com.matrix.android105_android.data.Repository.Home.Shops.Shop

interface IShopsRepository {

    suspend fun getShops():List<Shop>
}