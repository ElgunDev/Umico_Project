package com.matrix.android105_android.domain.Network.FireBase.Repository.shop.seller

import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.Seller

interface ISellerRepository {
    suspend fun getSeller():List<Seller>
}