package com.matrix.android105_android.domain.network.fireBase.repository.profil

interface IProfilImagesRepository {
    suspend fun uploadProfilImages(image:Any , uid:String):Result<Unit>
    suspend fun getProfilImages(uid: String):Result<String>
}