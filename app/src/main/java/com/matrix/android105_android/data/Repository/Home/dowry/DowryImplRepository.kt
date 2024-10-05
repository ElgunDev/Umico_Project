package com.matrix.android105_android.data.Repository.Home.dowry

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.Home.dowry.IDowryRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DowryImplRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
):IDowryRepository {
    override suspend fun getDowry(): List<String> {
        val result = fireStore.collection("dowry").get().await()
        return result.documents.map {
            it.getString("imageUrl")?:""
        }
    }
}