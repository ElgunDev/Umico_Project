package com.matrix.android105_android.domain.useCase.shop.seller

import com.matrix.android105_android.data.network.fireBase.Repository.shop.seller.Seller
import com.matrix.android105_android.domain.network.fireBase.repository.shop.seller.ISellerRepository
import javax.inject.Inject

class SellerUseCase @Inject constructor(
    private val sellerImplRepository: ISellerRepository
):ISellerUseCase {
    override suspend fun getSeller(): List<Seller> {
        return sellerImplRepository.getSeller()
    }

}