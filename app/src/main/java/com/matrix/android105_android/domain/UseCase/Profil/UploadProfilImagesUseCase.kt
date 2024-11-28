package com.matrix.android105_android.domain.UseCase.Profil

import android.net.Uri
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IProfilImagesRepository
import javax.inject.Inject

class UploadProfilImagesUseCase @Inject constructor(
    private val profilImagesRepository: IProfilImagesRepository
) {
    suspend   fun  invoke(images:Any , uid:String):Result<Unit>{
        return profilImagesRepository.uploadProfilImages(images,uid)
    }
}