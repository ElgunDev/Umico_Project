package com.matrix.android105_android.domain.Local.Repository.LikedProduct

import androidx.lifecycle.LiveData
import com.matrix.android105_android.data.Local.db.entity.LikedProductEntity

interface ILikedProductRepository {
    suspend fun addProductToDatabase(likedProductEntity: LikedProductEntity)
     fun getLikedProduct():LiveData<List<LikedProductEntity>>
    suspend fun deleteLikedProduct(productId:String)

    suspend fun isProductLiked(productId: String):Boolean
}