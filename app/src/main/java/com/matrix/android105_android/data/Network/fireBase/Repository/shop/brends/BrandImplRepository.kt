package com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Network.FireBase.Repository.shop.brends.IBrandRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BrandImplRepository @Inject constructor(
private val fireStore: FirebaseFirestore
   ):IBrandRepository {
    override suspend fun getBrand(): List<Brands> {
        val result = fireStore.collection("brends").get().await()
        return result.documents.map {
            val image = it.getString("imageUrl")?:""
            Brands(image)
        }
    }


}