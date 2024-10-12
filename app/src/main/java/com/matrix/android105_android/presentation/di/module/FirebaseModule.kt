package com.matrix.android105_android.presentation.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.UserImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.advertisement.AdImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.ShopsImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.dowry.DowryImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.ProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.AllProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.popular.PopularImplRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.advertisement.IAdRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.Shops.IShopsRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.dowry.IDowryRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.popular.IPopularRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IAllProductRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IProductRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepository(firestore: FirebaseFirestore): IUserRepository {
        return  UserImplRepository(firestore)
    }

    @Provides
    @Singleton
    fun provideAdRepository(firestore: FirebaseFirestore): IAdRepository {
        return AdImplRepository(firestore)
    }

    @Provides
    @Singleton
    fun provideShopRepository(firestore: FirebaseFirestore): IShopsRepository {
        return ShopsImplRepository(firestore)
    }

    @Provides
    @Singleton
    fun provideProductRepository(fireStore: FirebaseFirestore): IProductRepository {
        return ProductImplRepository(fireStore)
    }

    @Provides
    @Singleton
    fun provideDowryRepository(fireStore: FirebaseFirestore): IDowryRepository {
        return DowryImplRepository(fireStore)
    }
    @Provides
    @Singleton
    fun providePopularRepository(fireStore: FirebaseFirestore): IPopularRepository {
        return PopularImplRepository(fireStore)
    }
    @Provides
    @Singleton
    fun provideAllProductRepository(fireStore: FirebaseFirestore): IAllProductRepository {
        return AllProductImplRepository(fireStore)
    }
}