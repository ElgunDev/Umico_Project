package com.matrix.android105_android.domain.Network.FireBase.Repository.profil

interface IUserRepository {
    suspend fun getUsername(uid:String):Result<String>
}