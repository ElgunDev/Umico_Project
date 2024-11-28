package com.matrix.android105_android.domain.UseCase.shop.seller

import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.Seller
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.SellerImplRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.shop.seller.ISellerRepository
import javax.inject.Inject

class SellerUseCase @Inject constructor(
    private val sellerImplRepository: ISellerRepository
):ISellerUseCase {
    override suspend fun getSeller(): List<Seller> {
        return sellerImplRepository.getSeller()
    }

}