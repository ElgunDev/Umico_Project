package com.matrix.android105_android.domain.UseCase.Home.dowry

interface IDowryUseCase {

    suspend fun getDowry():List<String>
}