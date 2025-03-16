package com.matrix.android105_android.presentation.ui.basketProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.domain.local.repository.basketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.useCase.home.products.UpdateProductStockUseCase
import com.matrix.android105_android.domain.useCase.basketProduct.BasketProductUseCase
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val updateProductStockUseCase: UpdateProductStockUseCase,
    private val basketProductRepository: IBasketProductRepository,
    private val basketProductUseCase: BasketProductUseCase
) :ViewModel() {

    private val _basketProduct = MutableLiveData<NetworkResource<List<Product>>>()
    val basketProduct :LiveData<NetworkResource<List<Product>>>
        get() = _basketProduct


    fun increaseProductStock(productId:String , currentStock:Long ){
        viewModelScope.launch {
            updateProductStockUseCase.increaseStock(productId,currentStock)

        }
    }
    fun decreaseProductStock(productId:String , currentStock:Long ){
        viewModelScope.launch {
          updateProductStockUseCase.decreaseStock(productId,currentStock)

        }
    }
    fun updateProductQuantity(productId: String, newQuantity: Int) {
        viewModelScope.launch {
            basketProductRepository.updateBasketProductQuantity(productId, newQuantity)
        }
    }

    fun fetchBasketProduct(){
        viewModelScope.launch {
            val basketProducts = basketProductUseCase.getBasketProduct()
            _basketProduct.value = NetworkResource.Success(basketProducts)
        }
    }


}
