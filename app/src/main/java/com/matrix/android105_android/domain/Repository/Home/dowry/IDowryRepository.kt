package com.matrix.android105_android.domain.Repository.Home.dowry

interface IDowryRepository {
    suspend fun getDowry():List<String>
}