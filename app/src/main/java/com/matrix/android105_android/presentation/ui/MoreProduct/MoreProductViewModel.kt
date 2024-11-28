package com.matrix.android105_android.presentation.ui.MoreProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.domain.UseCase.Home.products.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreProductViewModel  @Inject constructor(
    private val getProductUseCase: GetProductUseCase
):ViewModel() {

    private val _moreProduct = MutableLiveData<List<Product>>()
    val moreProduct: MutableLiveData<List<Product>>
        get() = _moreProduct

    fun fetchMoreProduct(){
        try {
            viewModelScope.launch {
                val products = getProductUseCase.getProducts()
                _moreProduct.value = products
            }
        }
        catch (e:Exception){

        }
    }
}