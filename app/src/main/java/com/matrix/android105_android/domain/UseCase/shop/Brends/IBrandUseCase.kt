package com.matrix.android105_android.domain.UseCase.shop.Brends

import com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends.Brands

interface IBrandUseCase {

    suspend fun getBrands():List<Brands>
}