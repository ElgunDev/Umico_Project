package com.matrix.android105_android.domain.network.fireBase.repository.bonus.Partners

import com.matrix.android105_android.data.network.fireBase.Repository.bonus.partners.Partners

interface IPartnersRepository {

    suspend fun getPartners():List<Partners>
}