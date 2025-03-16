package com.matrix.android105_android.domain.network.fireBase.repository.home.advertisement

import com.matrix.android105_android.data.network.fireBase.Repository.home.advertisement.Advertisement

interface IAdRepository {
    suspend fun getAdvertisingImages():List<Advertisement>
}