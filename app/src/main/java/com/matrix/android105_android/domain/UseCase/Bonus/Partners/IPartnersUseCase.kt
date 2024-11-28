package com.matrix.android105_android.domain.UseCase.Bonus.Partners

import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.Partners

interface IPartnersUseCase {

    suspend fun getPartners():List<Partners>
}