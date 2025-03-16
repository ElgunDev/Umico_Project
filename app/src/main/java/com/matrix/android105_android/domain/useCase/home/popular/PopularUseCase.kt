package com.matrix.android105_android.domain.useCase.home.popular

import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.Popular
import com.matrix.android105_android.domain.network.fireBase.repository.home.popular.IPopularRepository
import javax.inject.Inject

class PopularUseCase @Inject constructor(
    private val iPopularRepository: IPopularRepository
):IPopularUseCase {
    override suspend fun getPopular(): List<Popular> {
        return iPopularRepository.getPopular()
    }
}