package com.matrix.android105_android.domain.Network.FireBase.Repository.Home.dowry

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.dowry.Dowry

interface IDowryRepository {
    suspend fun getDowry():List<Dowry>
}