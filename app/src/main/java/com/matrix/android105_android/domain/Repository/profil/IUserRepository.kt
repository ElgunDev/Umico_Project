package com.matrix.android105_android.domain.Repository.profil

interface IUserRepository {
    suspend fun getUsername(uid:String):Result<String>
}