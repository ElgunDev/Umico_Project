package com.matrix.android105_android.domain.useCase.shop.Brends

import com.matrix.android105_android.data.network.fireBase.Repository.shop.brends.Brands

interface IBrandUseCase {

    suspend fun getBrands():List<Brands>
}