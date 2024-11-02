package com.matrix.android105_android.data.Local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.android105_android.data.Local.db.entity.BasketProductEntity

@Dao
interface BasketProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertProduct(basketProductEntity: BasketProductEntity)

    @Query("SELECT * FROM basket_product" )
    fun getBasketProduct():LiveData<List<BasketProductEntity>>

    @Query("DELETE FROM basket_product WHERE id=:productId")
    suspend fun deleteProduct(productId:String)

    @Query("SELECT EXISTS(SELECT 1 FROM basket_product WHERE id=:productId)")
    suspend fun isProductBasket(productId: String):Boolean

    @Query("UPDATE BASKET_PRODUCT SET quantity=:newQuantity WHERE id=:productId")
    suspend fun updateQuantity(productId: String , newQuantity:Int)
}