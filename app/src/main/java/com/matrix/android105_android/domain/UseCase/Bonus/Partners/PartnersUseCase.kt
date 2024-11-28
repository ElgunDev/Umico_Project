package com.matrix.android105_android.domain.UseCase.Bonus.Partners

import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.Partners
import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.PartnersRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Bonus.Partners.IPartnersRepository
import javax.inject.Inject

class PartnersUseCase @Inject constructor(
    private val partnersRepository: IPartnersRepository
):IPartnersUseCase {
    override suspend fun getPartners(): List<Partners> {
        return partnersRepository.getPartners()
    }
}