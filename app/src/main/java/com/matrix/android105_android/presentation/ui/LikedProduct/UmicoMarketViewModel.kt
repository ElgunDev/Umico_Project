package com.matrix.android105_android.presentation.ui.LikedProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.data.Network.fireBase.Repository.likedProduct.LikedProductImplRepository
import com.matrix.android105_android.domain.UseCase.likedProduct.LikedProductUseCase
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UmicoMarketViewModel  @Inject constructor(
   private val likedProductUseCase: LikedProductUseCase
):ViewModel() {
    private val _likedProducts = MutableLiveData<NetworkResource<List<Product>>>()
    val likedProducts: MutableLiveData<NetworkResource<List<Product>>>
        get() = _likedProducts

    fun fetchLikedProducts(){
        viewModelScope.launch {
            val likedProducts = likedProductUseCase.getLikedProducts()
            _likedProducts.value =NetworkResource.Success(likedProducts)
        }
    }
}