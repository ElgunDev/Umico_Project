package com.matrix.android105_android.domain.network.fireBase.repository.shop.brends

import com.matrix.android105_android.data.network.fireBase.Repository.shop.brends.Brands

interface IBrandRepository {

    suspend fun getBrand():List<Brands>
}