package com.matrix.android105_android.domain.UseCase.Profil

import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.PersonalInformationRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.PersonalInformations
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IPersonalInformationRepository
import javax.inject.Inject

class UpdatePersonalInformationUseCase @Inject constructor(
    private val personalInformationRepository: IPersonalInformationRepository
) {

    suspend fun invoke(uid:String , personalInformations: PersonalInformations):Result<Unit>{
        return personalInformationRepository.updateProfilInformation(uid,personalInformations)
    }
}