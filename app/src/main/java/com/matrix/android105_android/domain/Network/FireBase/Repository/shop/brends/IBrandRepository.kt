package com.matrix.android105_android.domain.Network.FireBase.Repository.shop.brends

import com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends.Brands

interface IBrandRepository {

    suspend fun getBrand():List<Brands>
}