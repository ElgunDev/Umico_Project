package com.matrix.android105_android.domain.useCase.bonus.Partners

import com.matrix.android105_android.data.network.fireBase.Repository.bonus.partners.Partners
import com.matrix.android105_android.domain.network.fireBase.repository.bonus.Partners.IPartnersRepository
import javax.inject.Inject

class PartnersUseCase @Inject constructor(
    private val partnersRepository: IPartnersRepository
):IPartnersUseCase {
    override suspend fun getPartners(): List<Partners> {
        return partnersRepository.getPartners()
    }
}