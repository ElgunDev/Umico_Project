package com.matrix.android105_android.domain.Network.FireBase.Repository.profil

import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.PersonalInformations

interface IPersonalInformationRepository {

    suspend fun getPersonalInformation(uid:String):Result<PersonalInformations>

    suspend fun updateProfilInformation(uid:String , personalInformations: PersonalInformations):Result<Unit>
}