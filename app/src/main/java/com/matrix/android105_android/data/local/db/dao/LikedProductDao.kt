package com.matrix.android105_android.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.android105_android.data.local.db.entity.LikedProductEntity


@Dao
interface LikedProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(likedProductEntity: LikedProductEntity)

    @Query("SELECT * FROM liked_products")
     fun  getLikedProduct(): LiveData<List<LikedProductEntity>>
    @Query("DELETE FROM liked_products WHERE id = :productId ")
    suspend fun deleteLikedProduct(productId:String)

    @Query("SELECT EXISTS(SELECT 1 FROM liked_products WHERE id = :productId)")
    suspend fun isProductLiked(productId: String): Boolean
}