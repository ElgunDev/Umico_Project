package com.matrix.android105_android.domain.useCase.home.advertisement

import com.matrix.android105_android.data.network.fireBase.Repository.home.advertisement.Advertisement

interface IAdUseCase {
    suspend fun getAdvertisementImages():List<Advertisement>
}