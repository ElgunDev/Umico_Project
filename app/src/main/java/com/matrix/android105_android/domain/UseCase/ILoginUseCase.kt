package com.matrix.android105_android.domain.UseCase

import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider


interface ILoginUseCase {
    suspend fun sendVerificationCode(phoneNumber:String , callbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks )
    suspend fun verificationCodeAndLogin(credential: PhoneAuthCredential):Boolean
}