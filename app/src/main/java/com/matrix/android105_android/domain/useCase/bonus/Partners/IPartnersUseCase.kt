package com.matrix.android105_android.domain.useCase.bonus.Partners

import com.matrix.android105_android.data.network.fireBase.Repository.bonus.partners.Partners

interface IPartnersUseCase {

    suspend fun getPartners():List<Partners>
}