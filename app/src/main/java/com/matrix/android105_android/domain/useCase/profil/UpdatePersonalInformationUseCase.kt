package com.matrix.android105_android.domain.useCase.profil

import com.matrix.android105_android.data.network.fireBase.Repository.profil.PersonalInformations
import com.matrix.android105_android.domain.network.fireBase.repository.profil.IPersonalInformationRepository
import javax.inject.Inject

class UpdatePersonalInformationUseCase @Inject constructor(
    private val personalInformationRepository: IPersonalInformationRepository
) {

    suspend fun invoke(uid:String , personalInformations: PersonalInformations):Result<Unit>{
        return personalInformationRepository.updateProfilInformation(uid,personalInformations)
    }
}