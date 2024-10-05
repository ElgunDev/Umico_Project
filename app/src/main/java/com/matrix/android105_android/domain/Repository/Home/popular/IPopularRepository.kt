package com.matrix.android105_android.domain.Repository.Home.popular

import com.matrix.android105_android.data.Repository.Home.popular.Popular

interface IPopularRepository {

    suspend fun getPopular():List<Popular>
}