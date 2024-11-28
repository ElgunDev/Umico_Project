package com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Network.FireBase.Repository.shop.seller.ISellerRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SellerImplRepository  @Inject constructor(
    private val firestore: FirebaseFirestore
):ISellerRepository {
    override suspend fun getSeller(): List<Seller> {
      val result = firestore.collection("seller").get().await()
        return  result.documents.map {
            val images = it.getString("imageUrl")?:""
            Seller(images)
        }
    }

}