package com.matrix.android105_android.presentation.ui.services

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.network.fireBase.Repository.home.shops.Shop
import com.matrix.android105_android.domain.useCase.home.Shops.ShopsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val shopsUseCase: ShopsUseCase
):ViewModel() {

    private val _shops = MutableLiveData<List<Shop>>()
    val shops:MutableLiveData<List<Shop>>
        get() = _shops

    fun fetchShops(){
        try {
            viewModelScope.launch {
                val shopList = shopsUseCase.getShops()
                _shops.value = shopList
            }

        }
        catch (e:Exception){

        }

    }
}