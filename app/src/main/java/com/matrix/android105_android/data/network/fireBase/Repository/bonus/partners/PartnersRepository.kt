package com.matrix.android105_android.data.network.fireBase.Repository.bonus.partners

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.network.fireBase.repository.bonus.Partners.IPartnersRepository
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