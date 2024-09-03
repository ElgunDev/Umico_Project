package com.matrix.android105_android.presentation.di.module

import com.matrix.android105_android.data.Repository.Login.AuthImplRepository
import com.matrix.android105_android.domain.Repository.Login.IAuthRepository
import com.matrix.android105_android.domain.UseCase.ILoginUseCase
import com.matrix.android105_android.domain.UseCase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthImplRepository
    ): IAuthRepository

    @Binds
    @Singleton
    abstract fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): ILoginUseCase
}