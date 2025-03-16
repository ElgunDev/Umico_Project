package com.matrix.android105_android.domain.useCase.home.popular

import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.Popular

interface IPopularUseCase {

    suspend fun getPopular():List<Popular>
}