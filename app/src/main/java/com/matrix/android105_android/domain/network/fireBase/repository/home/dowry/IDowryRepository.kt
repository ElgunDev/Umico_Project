package com.matrix.android105_android.domain.network.fireBase.repository.home.dowry

import com.matrix.android105_android.data.network.fireBase.Repository.home.dowry.Dowry

interface IDowryRepository {
    suspend fun getDowry():List<Dowry>
}