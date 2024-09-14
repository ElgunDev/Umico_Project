package com.matrix.android105_android.domain.UseCase.Home.Shops

import com.matrix.android105_android.data.Repository.Home.Shops.Shop

interface IShopsUseCase {
    suspend fun getShops():List<Shop>
}