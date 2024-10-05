package com.matrix.android105_android.data.Repository.Home.popular

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.Home.popular.IPopularRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PopularImplRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
):IPopularRepository {
    override suspend fun getPopular(): List<Popular> {
        val result = fireStore.collection("popular").get().await()
        return result.documents.map {
            val imageUrl = it.getString("imageUrl")?:""
            val text = it.getString("text")?:""
            Popular(imageUrl,text)
        }
    }

}