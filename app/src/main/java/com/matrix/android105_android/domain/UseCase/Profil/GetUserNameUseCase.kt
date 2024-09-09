package com.matrix.android105_android.domain.UseCase.Profil

import com.matrix.android105_android.domain.Repository.profil.IUserRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend fun invoke(uid:String):Result<String>{
        return userRepository.getUsername(uid)
    }
}