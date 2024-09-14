package com.matrix.android105_android.data.Repository.Home.Shops

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.Home.Shops.IShopsRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ShopsImplRepository @Inject constructor(
   private val fireStore: FirebaseFirestore
) : IShopsRepository {
    override suspend fun getShops(): List<Shop> {
        val result = fireStore.collection("shops").get().await()
        return result.documents.map {document->
           val imageUrl= document.getString("imageUrl")?:""
           val nameTop =document.getString("nameTop")?:""
            val nameBottom = document.getString("nameBottom")?:""
            Shop(imageUrl, nameBottom,nameTop)
        }
    }
}