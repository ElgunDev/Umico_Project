package com.matrix.android105_android.domain.UseCase.Home.advertisement

import com.matrix.android105_android.data.Repository.Home.advertisement.Advertisement
import com.matrix.android105_android.domain.Repository.Home.advertisement.IAdRepository
import javax.inject.Inject

class AdUseCase @Inject constructor(
    private val adRepository: IAdRepository
) : IAdUseCase {
    override suspend fun getAdvertisementImages(): List<Advertisement> {
         return adRepository.getAdvertisingImages()
    }
}