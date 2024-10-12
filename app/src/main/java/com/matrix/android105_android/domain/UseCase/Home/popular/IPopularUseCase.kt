package com.matrix.android105_android.domain.UseCase.Home.popular

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.popular.Popular

interface IPopularUseCase {

    suspend fun getPopular():List<Popular>
}