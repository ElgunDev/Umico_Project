package com.matrix.android105_android.presentation.ui.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.Popular
import com.matrix.android105_android.data.network.fireBase.Repository.shop.brends.Brands
import com.matrix.android105_android.data.network.fireBase.Repository.shop.seller.Seller
import com.matrix.android105_android.domain.useCase.home.popular.PopularUseCase
import com.matrix.android105_android.domain.useCase.home.products.GetProductUseCase
import com.matrix.android105_android.domain.useCase.shop.Brends.BrandsUseCase
import com.matrix.android105_android.domain.useCase.shop.seller.SellerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase,
    private val productUseCase: GetProductUseCase,
    private val brandsUseCase: BrandsUseCase,
    private val sellerUseCase: SellerUseCase
):ViewModel() {
    private val _popularItems = MutableLiveData<List<Popular>>()
    val popularItems : MutableLiveData<List<Popular>>
        get()= _popularItems

    private val _products= MutableLiveData<List<Product>>()
    val products:MutableLiveData<List<Product>>
        get() = _products



    private val _innovationProduct =MutableLiveData<List<Product>>()
    val innovationProduct:MutableLiveData<List<Product>>
        get() = _innovationProduct

    private val _brands = MutableLiveData<List<Brands>>()
    val brands: MutableLiveData<List<Brands>>
        get()= _brands

    private val _seller = MutableLiveData<List<Seller>>()
    val seller: MutableLiveData<List<Seller>>
        get()= _seller


    fun fetchPopularItems(){
        viewModelScope.launch {
            try {
                val popularItems = popularUseCase.getPopular()
                _popularItems.value = popularItems
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchProducts(){
        try {
            viewModelScope.launch {
                val products = productUseCase.getProducts()
                val filterProduct = products.filter {
                    it.price>500
                }
                _products.value = filterProduct
            }

        }
        catch (e:Exception){

        }
    }

    fun fetchBrands(){
        try {
            viewModelScope.launch {
                val brands = brandsUseCase.getBrands()
                _brands.value = brands
            }
        }
        catch (e:Exception){

        }
    }
    fun fetchSeller(){
        try {
            viewModelScope.launch {
                val seller = sellerUseCase.getSeller()
                _seller.value = seller
            }
        }
        catch (e:Exception){

        }
    }



    fun fetchInnovationProducts(){
        try {
            viewModelScope.launch {
                val products = productUseCase.getProducts()
                val filterProduct = products.filter {
                     it.category == "telefon" || it.category =="saat" || it.category == "təmizlik"
                }
                _innovationProduct.value = filterProduct
            }

        }
        catch (e:Exception){

        }
    }

}