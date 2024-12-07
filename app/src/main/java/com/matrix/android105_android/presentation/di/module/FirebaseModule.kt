package com.matrix.android105_android.presentation.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.PartnersRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.UserImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.advertisement.AdImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.ShopsImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.dowry.DowryImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.ProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.AllProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.popular.PopularImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.PersonalInformationRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Profil.ProfilImagesRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.basketProduct.BasketProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.likedProduct.LikedProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends.BrandImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.SellerImplRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Bonus.Partners.IPartnersRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.advertisement.IAdRepository
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.Shops.IShopsRepository
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
    fun provideFirebaseStorage():FirebaseStorage{
        return FirebaseStorage.getInstance()
    }


}