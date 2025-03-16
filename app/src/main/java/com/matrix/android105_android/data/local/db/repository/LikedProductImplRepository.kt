package com.matrix.android105_android.data.local.db.repository

import androidx.lifecycle.LiveData
import com.matrix.android105_android.data.local.db.dao.LikedProductDao
import com.matrix.android105_android.data.local.db.entity.LikedProductEntity
import com.matrix.android105_android.domain.local.repository.likedProduct.ILikedProductRepository
import javax.inject.Inject

class LikedProductImplRepository @Inject constructor(
    private val likedProductDao: LikedProductDao
):ILikedProductRepository {
    override suspend fun addProductToDatabase(likedProductEntity: LikedProductEntity) {
        likedProductDao.insertProduct(likedProductEntity)
    }

    override  fun getLikedProduct():LiveData<List<LikedProductEntity>> {
        return likedProductDao.getLikedProduct()
    }

    override suspend fun deleteLikedProduct(productId: String) {
       likedProductDao.deleteLikedProduct(productId)
    }

    override suspend fun isProductLiked(productId: String):Boolean {
       return  likedProductDao.isProductLiked(productId)
    }

}