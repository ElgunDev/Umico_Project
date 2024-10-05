package com.matrix.android105_android.domain.Repository.Home.advertisement

import com.matrix.android105_android.data.Repository.Home.advertisement.Advertisement

interface IAdRepository {
    suspend fun getAdvertisingImages():List<Advertisement>
}