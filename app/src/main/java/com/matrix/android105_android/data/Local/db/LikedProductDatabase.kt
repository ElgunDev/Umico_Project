package com.matrix.android105_android.data.Local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrix.android105_android.data.Local.db.dao.BasketProductDao
import com.matrix.android105_android.data.Local.db.dao.LikedProductDao
import com.matrix.android105_android.data.Local.db.entity.BasketProductEntity
import com.matrix.android105_android.data.Local.db.entity.LikedProductEntity

@Database(entities = [LikedProductEntity::class , BasketProductEntity::class] , version = 4)
abstract class LikedProductDatabase:RoomDatabase() {
    abstract fun likedProductDao():LikedProductDao
    abstract fun basketProductDao():BasketProductDao
}