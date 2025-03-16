package com.matrix.android105_android.domain.network.fireBase.repository.profil

interface IUserRepository {
    suspend fun getUsername(uid:String):Result<String>
}