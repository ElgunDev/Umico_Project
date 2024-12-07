package com.matrix.android105_android.presentation.di.module

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.PartnersRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.AllProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.ProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.ShopsImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.advertisement.AdImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.dowry.DowryImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.popular.PopularImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.PersonalInformationRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.ProfilImagesRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.UserImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.basketProduct.BasketProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.likedProduct.LikedProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends.BrandImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.SellerImplRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Bonus.Partners.IPartnersRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.Shops.IShopsRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.advertisement.IAdRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.dowry.IDowryRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.popular.IPopularRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IAllProductRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IProductRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.basketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.likedProduct.ILikedProductRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IPersonalInformationRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IProfilImagesRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.profil.IUserRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.shop.brends.IBrandRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.shop.seller.ISellerRepository
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