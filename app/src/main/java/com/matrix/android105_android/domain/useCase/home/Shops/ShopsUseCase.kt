package com.matrix.android105_android.domain.useCase.home.Shops

import com.matrix.android105_android.data.network.fireBase.Repository.home.shops.Shop
import com.matrix.android105_android.domain.network.fireBase.repository.home.Shops.IShopsRepository
import javax.inject.Inject

class ShopsUseCase @Inject constructor(
   private val shopRepository: IShopsRepository
) :IShopsUseCase {
    override suspend fun getShops(): List<Shop> {
       return shopRepository.getShops()
    }
}