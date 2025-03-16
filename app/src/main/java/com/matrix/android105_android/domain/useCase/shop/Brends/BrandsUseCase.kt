package com.matrix.android105_android.domain.useCase.shop.Brends

import com.matrix.android105_android.data.network.fireBase.Repository.shop.brends.Brands
import com.matrix.android105_android.domain.network.fireBase.repository.shop.brends.IBrandRepository
import javax.inject.Inject

class BrandsUseCase @Inject constructor(
    private val brandImplRepository: IBrandRepository
) :IBrandUseCase{
    override suspend fun getBrands(): List<Brands> {
        return brandImplRepository.getBrand()
    }

}