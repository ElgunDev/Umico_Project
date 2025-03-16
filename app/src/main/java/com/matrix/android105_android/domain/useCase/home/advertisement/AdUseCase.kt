package com.matrix.android105_android.domain.useCase.home.advertisement

import com.matrix.android105_android.data.network.fireBase.Repository.home.advertisement.Advertisement
import com.matrix.android105_android.domain.network.fireBase.repository.home.advertisement.IAdRepository
import javax.inject.Inject

class AdUseCase @Inject constructor(
    private val adRepository: IAdRepository
) : IAdUseCase {
    override suspend fun getAdvertisementImages(): List<Advertisement> {
         return adRepository.getAdvertisingImages()
    }
}