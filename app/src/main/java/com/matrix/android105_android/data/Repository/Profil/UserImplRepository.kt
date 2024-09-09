package com.matrix.android105_android.data.Repository.Profil

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.profil.IUserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserImplRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) :IUserRepository {
    override suspend fun getUsername(uid: String): Result<String> {
        return try {
            val documentSnapshot = fireStore.collection("users").document(uid).get().await()
            val name = documentSnapshot.getString("name")?:"no name"
            Result.success(name)
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }
}