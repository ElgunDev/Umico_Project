package com.matrix.android105_android.presentation.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.matrix.android105_android.data.Local.db.LikedProductDatabase
import com.matrix.android105_android.data.Local.db.dao.BasketProductDao
import com.matrix.android105_android.data.Local.db.dao.LikedProductDao
import com.matrix.android105_android.data.Local.db.repository.BasketProductImplRepository
import com.matrix.android105_android.data.Local.db.repository.LikedProductImplRepository
import com.matrix.android105_android.domain.Local.Repository.BasketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.Local.Repository.LikedProduct.ILikedProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLikedDatabase(@ApplicationContext appContext:Context):LikedProductDatabase{
        return Room.databaseBuilder(
            appContext,
            LikedProductDatabase::class.java,
            "liked_priduct_database"
        )
            .build()

    }
    @Provides
    fun provideLikedProductDao(database:LikedProductDatabase):LikedProductDao{
        return database.likedProductDao()

    }
    @Provides
    fun provideLikedProductRepository(
        likedProductDao: LikedProductDao
    ): ILikedProductRepository {
        return LikedProductImplRepository(likedProductDao)
    }

    @Provides
    fun provideBasketProductDao(database: LikedProductDatabase):BasketProductDao{
        return database.basketProductDao()
    }

    @Provides
    fun provideBasketProductRepository(basketProductDao: BasketProductDao):IBasketProductRepository{
        return BasketProductImplRepository(basketProductDao)
    }
}