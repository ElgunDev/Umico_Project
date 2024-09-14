package com.matrix.android105_android.domain.UseCase.Home.Shops

import com.matrix.android105_android.data.Repository.Home.Shops.Shop
import com.matrix.android105_android.domain.Repository.Home.Shops.IShopsRepository
import javax.inject.Inject

class ShopsUseCase @Inject constructor(
   private val shopRepository:IShopsRepository
) :IShopsUseCase {
    override suspend fun getShops(): List<Shop> {
       return shopRepository.getShops()
    }
}