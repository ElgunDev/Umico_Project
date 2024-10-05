package com.matrix.android105_android.domain.UseCase.Home.popular

import com.matrix.android105_android.data.Repository.Home.popular.Popular
import com.matrix.android105_android.domain.Repository.Home.popular.IPopularRepository
import javax.inject.Inject

class PopularUseCase @Inject constructor(
    private val iPopularRepository: IPopularRepository
):IPopularUseCase {
    override suspend fun getPopular(): List<Popular> {
        return iPopularRepository.getPopular()
    }
}