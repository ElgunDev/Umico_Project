package com.matrix.android105_android.presentation.di.module

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.matrix.android105_android.data.network.fireBase.Repository.bonus.partners.PartnersRepository
import com.matrix.android105_android.data.network.fireBase.Repository.home.products.AllProductImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.home.products.ProductImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.home.shops.ShopsImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.home.advertisement.AdImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.home.dowry.DowryImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.PopularImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.profil.PersonalInformationRepository
import com.matrix.android105_android.data.network.fireBase.Repository.profil.ProfilImagesRepository
import com.matrix.android105_android.data.network.fireBase.Repository.profil.UserImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.basketProduct.BasketProductImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.likedProduct.LikedProductImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.shop.brends.BrandImplRepository
import com.matrix.android105_android.data.network.fireBase.Repository.shop.seller.SellerImplRepository
import com.matrix.android105_android.domain.network.fireBase.repository.bonus.Partners.IPartnersRepository
import com.matrix.android105_android.domain.network.fireBase.repository.home.Shops.IShopsRepository
import com.matrix.android105_android.domain.network.fireBase.repository.home.advertisement.IAdRepository
import com.matrix.android105_android.domain.network.fireBase.repository.home.dowry.IDowryRepository
import com.matrix.android105_android.domain.network.fireBase.repository.home.popular.IPopularRepository
import com.matrix.android105_android.domain.network.fireBase.repository.home.product.IAllProductRepository
import com.matrix.android105_android.domain.network.fireBase.repository.home.product.IProductRepository
import com.matrix.android105_android.domain.network.fireBase.repository.basketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.network.fireBase.repository.likedProduct.ILikedProductRepository
import com.matrix.android105_android.domain.network.fireBase.repository.profil.IPersonalInformationRepository
import com.matrix.android105_android.domain.network.fireBase.repository.profil.IProfilImagesRepository
import com.matrix.android105_android.domain.network.fireBase.repository.profil.IUserRepository
import com.matrix.android105_android.domain.network.fireBase.repository.shop.brends.IBrandRepository
import com.matrix.android105_android.domain.network.fireBase.repository.shop.seller.ISellerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
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
    fun provideProfilImagesRepository(firebaseStorage: FirebaseStorage, firestore: FirebaseFirestore): IProfilImagesRepository {
        return ProfilImagesRepository(firestore , firebaseStorage)
    }

    @Provides
    @Singleton
    fun providePersonalInformationRepository(fireStore: FirebaseFirestore): IPersonalInformationRepository {
        return PersonalInformationRepository(fireStore)
    }

    @Provides
    @Singleton
    fun provideLikedProductRepository(fireStore: FirebaseFirestore): ILikedProductRepository {
        return LikedProductImplRepository(fireStore)
    }

    @Provides
    @Singleton
    fun provideBasketProductRepository(fireStore: FirebaseFirestore): IBasketProductRepository {
        return BasketProductImplRepository(fireStore)
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
    fun providePartnersRepository(fireStore: FirebaseFirestore): IPartnersRepository {
        return PartnersRepository(fireStore)
    }

    @Provides
    @Singleton
    fun provideBrandsRepository(fireStore: FirebaseFirestore): IBrandRepository {
        return BrandImplRepository(fireStore)
    }

    @Provides
    @Singleton
    fun provideSellerRepository(fireStore: FirebaseFirestore): ISellerRepository {
        return SellerImplRepository(fireStore)
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