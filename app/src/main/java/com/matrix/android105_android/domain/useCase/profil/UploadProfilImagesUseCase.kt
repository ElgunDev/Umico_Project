package com.matrix.android105_android.domain.useCase.profil

import com.matrix.android105_android.domain.network.fireBase.repository.profil.IProfilImagesRepository
import javax.inject.Inject

class UploadProfilImagesUseCase @Inject constructor(
    private val profilImagesRepository: IProfilImagesRepository
) {
    suspend   fun  invoke(images:Any , uid:String):Result<Unit>{
        return profilImagesRepository.uploadProfilImages(images,uid)
    }
}