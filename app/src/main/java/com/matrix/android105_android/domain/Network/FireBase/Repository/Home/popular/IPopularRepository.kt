package com.matrix.android105_android.domain.Network.FireBase.Repository.Home.popular

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.popular.Popular

interface IPopularRepository {

    suspend fun getPopular():List<Popular>
}