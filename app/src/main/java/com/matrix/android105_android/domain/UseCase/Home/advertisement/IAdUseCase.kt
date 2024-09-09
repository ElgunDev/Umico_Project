package com.matrix.android105_android.domain.UseCase.Home.advertisement

interface IAdUseCase {
    suspend fun getAdvertisementImages():List<String>
}