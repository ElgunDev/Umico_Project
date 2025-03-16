package com.matrix.android105_android.domain.useCase.profil

import com.matrix.android105_android.domain.network.fireBase.repository.profil.IProfilImagesRepository
import javax.inject.Inject

class GetProfilImagesUseCase @Inject constructor(
    private val profilImagesRepository: IProfilImagesRepository
) {
    suspend  fun invoke(uid:String):Result<String?>{
       return profilImagesRepository.getProfilImages(uid)
    }
}