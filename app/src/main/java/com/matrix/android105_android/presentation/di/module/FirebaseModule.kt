package com.matrix.android105_android.presentation.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.data.Repository.Profil.UserImplRepository
import com.matrix.android105_android.data.Repository.Home.advertisement.AdImplRepository
import com.matrix.android105_android.domain.Repository.Home.advertisement.IAdRepository
import com.matrix.android105_android.domain.Repository.profil.IUserRepository
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
    fun provideUserRepository(firestore: FirebaseFirestore):IUserRepository{
        return  UserImplRepository(firestore)
    }

    @Provides
    @Singleton
    fun provideAdRepository(firestore: FirebaseFirestore): IAdRepository {
        return AdImplRepository(firestore)
    }
}