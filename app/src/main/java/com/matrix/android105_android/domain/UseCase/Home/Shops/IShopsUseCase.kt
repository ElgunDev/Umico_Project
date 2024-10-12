package com.matrix.android105_android.domain.UseCase.Home.Shops

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.Shop

interface IShopsUseCase {
    suspend fun getShops():List<Shop>
}