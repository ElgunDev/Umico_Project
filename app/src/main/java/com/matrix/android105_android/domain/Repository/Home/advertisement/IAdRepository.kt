package com.matrix.android105_android.domain.Repository.Home.advertisement

interface IAdRepository {
    suspend fun getAdvertisingImages():List<String>
}