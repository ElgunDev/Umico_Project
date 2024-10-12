package com.matrix.android105_android.domain.Network.FireBase.Repository.Home.Shops

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.Shop

interface IShopsRepository {

    suspend fun getShops():List<Shop>
}