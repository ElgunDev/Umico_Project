package com.matrix.android105_android.domain.UseCase.Profil

import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.ProfilImagesRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IProfilImagesRepository
import javax.inject.Inject

class GetProfilImagesUseCase @Inject constructor(
    private val profilImagesRepository: IProfilImagesRepository
) {
    suspend  fun invoke(uid:String):Result<String?>{
       return profilImagesRepository.getProfilImages(uid)
    }
}