package com.matrix.android105_android.domain.UseCase.shop.Brends

import com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends.Brands
import com.matrix.android105_android.domain.Network.FireBase.Repository.shop.brends.IBrandRepository
import javax.inject.Inject

class BrandsUseCase @Inject constructor(
    private val brandImplRepository: IBrandRepository
) :IBrandUseCase{
    override suspend fun getBrands(): List<Brands> {
        return brandImplRepository.getBrand()
    }

}