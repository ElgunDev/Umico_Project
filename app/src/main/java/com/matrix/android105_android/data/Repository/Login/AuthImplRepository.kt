package com.matrix.android105_android.data.Repository.Login

import android.app.Activity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.Login.IAuthRepository
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthImplRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
):IAuthRepository {
    override suspend fun sendVerificationCode(
        phoneNumber: String,
        callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L , TimeUnit.SECONDS)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    override suspend fun signInWithCredential(credential: PhoneAuthCredential): FirebaseUser? {
       return try {
           val authResult = firebaseAuth.signInWithCredential(credential).await()
           authResult.user
       }
       catch (e:Exception){
           null
       }
    }

    override suspend fun saveUserToFirestore(user: FirebaseUser, additionalData: Map<String, Any>) {
        val userMap = mapOf(
            "uid" to user.uid,
            "phoneNumber" to user.phoneNumber
        )+additionalData
        try {
            firestore.collection("users").document(user.uid).set(userMap).await()
        }
        catch (e:Exception){
        }
    }

    override suspend fun checkUserExist(uid: String): Boolean {
      return try {
          val document = firestore.collection("users").document(uid).get().await()
           document.exists()
      }
      catch (e:Exception){
          false
      }
    }
}