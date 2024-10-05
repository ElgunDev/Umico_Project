package com.matrix.android105_android.domain.UseCase.Home.dowry

import com.matrix.android105_android.domain.Repository.Home.dowry.IDowryRepository
import javax.inject.Inject

class DowryUseCase @Inject constructor(
    private val dowryRepository: IDowryRepository
):IDowryUseCase {
    override suspend fun getDowry(): List<String> {
        return dowryRepository.getDowry()
    }


}