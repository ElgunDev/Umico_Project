package com.matrix.android105_android.presentation.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.matrix.android105_android.data.local.db.LikedProductDatabase
import com.matrix.android105_android.data.local.db.dao.BasketProductDao
import com.matrix.android105_android.data.local.db.dao.LikedProductDao
import com.matrix.android105_android.data.local.db.repository.BasketProductImplRepository
import com.matrix.android105_android.data.local.db.repository.LikedProductImplRepository
import com.matrix.android105_android.domain.local.repository.basketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.local.repository.likedProduct.ILikedProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    val MIGRATION_2_4 = object : Migration(2, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE LikedProduct ADD COLUMN quantity INTEGER NOT NULL DEFAULT 1")
        }
    }

    @Provides
    @Singleton
    fun provideLikedDatabase(@ApplicationContext appContext:Context):LikedProductDatabase{
        return Room.databaseBuilder(
            appContext,
            LikedProductDatabase::class.java,
            "liked_priduct_database"
        )
            .addMigrations(MIGRATION_2_4)
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