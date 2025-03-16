package com.matrix.android105_android.domain.local.repository.likedProduct

import androidx.lifecycle.LiveData
import com.matrix.android105_android.data.local.db.entity.LikedProductEntity

interface ILikedProductRepository {
    suspend fun addProductToDatabase(likedProductEntity: LikedProductEntity)
     fun getLikedProduct():LiveData<List<LikedProductEntity>>
    suspend fun deleteLikedProduct(productId:String)

    suspend fun isProductLiked(productId: String):Boolean
}