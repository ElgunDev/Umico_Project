package com.matrix.android105_android.domain.UseCase.Home.advertisement

import com.matrix.android105_android.data.Repository.Home.advertisement.Advertisement

interface IAdUseCase {
    suspend fun getAdvertisementImages():List<Advertisement>
}