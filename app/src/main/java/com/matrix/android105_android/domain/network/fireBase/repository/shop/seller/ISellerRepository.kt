package com.matrix.android105_android.domain.network.fireBase.repository.shop.seller

import com.matrix.android105_android.data.network.fireBase.Repository.shop.seller.Seller

interface ISellerRepository {
    suspend fun getSeller():List<Seller>
}