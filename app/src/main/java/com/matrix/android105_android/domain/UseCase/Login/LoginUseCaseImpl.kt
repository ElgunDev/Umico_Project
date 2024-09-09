package com.matrix.android105_android.domain.UseCase.Login

import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.matrix.android105_android.domain.Repository.Login.IAuthRepository
import com.matrix.android105_android.domain.UseCase.Login.ILoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    val iAuthRepository: IAuthRepository
): ILoginUseCase {
    override suspend fun sendVerificationCode(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    )
    {
        iAuthRepository.sendVerificationCode(phoneNumber,callbacks)
    }

    override suspend fun verificationCodeAndLogin(credential: PhoneAuthCredential): Boolean {
        val user = iAuthRepository.signInWithCredential(credential)
        return if (user!=null){
            if (!iAuthRepository.checkUserExist(user.uid)){
                iAuthRepository.saveUserToFirestore(user , emptyMap())
            }
            true
        }
        else{
            false
        }
    }
}