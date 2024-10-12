package com.matrix.android105_android.domain.UseCase.Home.dowry

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.dowry.Dowry

interface IDowryUseCase {

    suspend fun getDowry():List<Dowry>
}