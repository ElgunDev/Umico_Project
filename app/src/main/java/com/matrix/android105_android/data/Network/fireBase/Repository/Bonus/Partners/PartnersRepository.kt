package com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Network.FireBase.Repository.Bonus.Partners.IPartnersRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PartnersRepository @Inject constructor(
    private val fireStore: FirebaseFirestore

):IPartnersRepository {
    override suspend fun getPartners(): List<Partners> {
        val result =fireStore.collection("Partners").get().await()
        return result.documents.map {
            val images = it.getString("imageUrl")?:""
            Partners(images)
        }
    }
}