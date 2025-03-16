package com.matrix.android105_android.domain.network.fireBase.repository.home.Shops

import com.matrix.android105_android.data.network.fireBase.Repository.home.shops.Shop

interface IShopsRepository {

    suspend fun getShops():List<Shop>
}