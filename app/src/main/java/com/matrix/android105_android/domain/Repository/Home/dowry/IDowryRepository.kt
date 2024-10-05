package com.matrix.android105_android.domain.Repository.Home.dowry

import com.matrix.android105_android.data.Repository.Home.dowry.Dowry

interface IDowryRepository {
    suspend fun getDowry():List<Dowry>
}