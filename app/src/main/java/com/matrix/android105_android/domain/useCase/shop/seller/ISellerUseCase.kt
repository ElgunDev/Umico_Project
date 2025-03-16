package com.matrix.android105_android.domain.useCase.shop.seller

import com.matrix.android105_android.data.network.fireBase.Repository.shop.seller.Seller

interface ISellerUseCase {
    suspend fun getSeller():List<Seller>
}