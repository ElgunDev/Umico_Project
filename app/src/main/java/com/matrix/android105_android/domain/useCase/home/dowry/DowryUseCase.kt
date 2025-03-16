package com.matrix.android105_android.domain.useCase.home.dowry

import com.matrix.android105_android.data.network.fireBase.Repository.home.dowry.Dowry
import com.matrix.android105_android.domain.network.fireBase.repository.home.dowry.IDowryRepository
import javax.inject.Inject

class DowryUseCase @Inject constructor(
    private val dowryRepository: IDowryRepository
):IDowryUseCase {
    override suspend fun getDowry(): List<Dowry> {
        return dowryRepository.getDowry()
    }


}