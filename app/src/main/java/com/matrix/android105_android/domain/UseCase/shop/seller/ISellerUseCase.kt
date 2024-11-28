package com.matrix.android105_android.domain.UseCase.shop.seller

import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.Seller

interface ISellerUseCase {
    suspend fun getSeller():List<Seller>
}