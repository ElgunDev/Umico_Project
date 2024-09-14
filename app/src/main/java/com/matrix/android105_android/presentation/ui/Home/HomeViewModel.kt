package com.matrix.android105_android.presentation.ui.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.matrix.android105_android.data.Repository.Home.Shops.Shop
import com.matrix.android105_android.domain.UseCase.Home.Shops.ShopsUseCase
import com.matrix.android105_android.domain.UseCase.Profil.GetUserNameUseCase
import com.matrix.android105_android.domain.UseCase.Home.advertisement.AdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserNameUseCase:GetUserNameUseCase,
    private val firebaseAuth: FirebaseAuth,
    private val adUseCase: AdUseCase,
    private val shopUseCase:ShopsUseCase
):ViewModel() {
    private val _userName = MutableLiveData<String>()
    val username:MutableLiveData<String>
        get() = _userName

    private val _adImages = MutableLiveData<List<String>>()
    val adImages:MutableLiveData<List<String>>
        get() = _adImages

    private val _shops= MutableLiveData<List<Shop>>()
    val shops:MutableLiveData<List<Shop>>
        get() = _shops

    fun fetchUserName(){
        val uid = firebaseAuth.currentUser?.uid
                if(uid !=null){
                    viewModelScope.launch {
                        val result = getUserNameUseCase.invoke(uid)
                        result.onSuccess {name->
                            _userName.value = name
                        }
                            .onFailure {exception->
                                _userName.value = "Error : ${exception.message}"
                            }
                    }
                }
    }
    fun fetchAdImages(){
        viewModelScope.launch {
            try {
                val images = adUseCase.getAdvertisementImages()
                _adImages.value = images
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchShops(){
        viewModelScope.launch {
            try {
                val shopList = shopUseCase.getShops()
                _shops.value = shopList
            }
            catch (e:Exception){

            }
        }
    }
}