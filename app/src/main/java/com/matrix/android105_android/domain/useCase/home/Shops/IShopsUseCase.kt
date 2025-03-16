package com.matrix.android105_android.domain.useCase.home.Shops

import com.matrix.android105_android.data.network.fireBase.Repository.home.shops.Shop

interface IShopsUseCase {
    suspend fun getShops():List<Shop>
}