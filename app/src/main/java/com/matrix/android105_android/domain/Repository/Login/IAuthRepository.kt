package com.matrix.android105_android.domain.Repository.Login

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider


interface IAuthRepository {
    suspend fun sendVerificationCode(phoneNumber:String , callback:PhoneAuthProvider.OnVerificationStateChangedCallbacks)
    suspend fun signInWithCredential(credential:PhoneAuthCredential):FirebaseUser?
    suspend fun saveUserToFirestore(user: FirebaseUser , additionalData:Map<String,Any>)
    suspend fun checkUserExist(uid:String):Boolean
}