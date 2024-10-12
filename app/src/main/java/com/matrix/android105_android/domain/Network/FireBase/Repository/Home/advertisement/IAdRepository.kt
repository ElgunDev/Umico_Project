package com.matrix.android105_android.domain.Network.FireBase.Repository.Home.advertisement

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.advertisement.Advertisement

interface IAdRepository {
    suspend fun getAdvertisingImages():List<Advertisement>
}