package com.matrix.android105_android.domain.network.fireBase.repository.profil

import com.matrix.android105_android.data.network.fireBase.Repository.profil.PersonalInformations

interface IPersonalInformationRepository {

    suspend fun getPersonalInformation(uid:String):Result<PersonalInformations>

    suspend fun updateProfilInformation(uid:String , personalInformations: PersonalInformations):Result<Unit>
}