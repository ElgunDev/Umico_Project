package com.matrix.android105_android.domain.useCase.home.dowry

import com.matrix.android105_android.data.network.fireBase.Repository.home.dowry.Dowry

interface IDowryUseCase {

    suspend fun getDowry():List<Dowry>
}