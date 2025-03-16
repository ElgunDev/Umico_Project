package com.matrix.android105_android.domain.network.fireBase.repository.home.popular

import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.Popular

interface IPopularRepository {

    suspend fun getPopular():List<Popular>
}