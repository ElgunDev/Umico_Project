package com.matrix.android105_android.domain.Network.FireBase.Repository.profil

import android.net.Uri

interface IProfilImagesRepository {
    suspend fun uploadProfilImages(image:Any , uid:String):Result<Unit>
    suspend fun getProfilImages(uid: String):Result<String>
}