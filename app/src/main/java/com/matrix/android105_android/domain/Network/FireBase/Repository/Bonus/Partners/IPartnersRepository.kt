package com.matrix.android105_android.domain.Network.FireBase.Repository.Bonus.Partners

import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.Partners

interface IPartnersRepository {

    suspend fun getPartners():List<Partners>
}