package com.matrix.android105_android.data.Repository.Home.advertisement

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.Home.advertisement.IAdRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AdImplRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
): IAdRepository {
    override suspend fun getAdvertisingImages(): List<String> {
        val result = fireStore.collection("advertisements").get().await()
        return result.documents.map {
            it.getString("imageUrl")?:""
        }
    }
}