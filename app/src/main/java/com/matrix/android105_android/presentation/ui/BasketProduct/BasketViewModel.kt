package com.matrix.android105_android.presentation.ui.BasketProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.domain.Local.Repository.BasketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.UseCase.Home.products.UpdateProductStockUseCase
import com.matrix.android105_android.domain.UseCase.basketProduct.BasketProductUseCase
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
