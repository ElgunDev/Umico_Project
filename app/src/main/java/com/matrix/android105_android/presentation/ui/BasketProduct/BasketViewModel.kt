package com.matrix.android105_android.presentation.ui.BasketProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.domain.Local.Repository.BasketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.UseCase.Home.products.UpdateProductStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val updateProductStockUseCase: UpdateProductStockUseCase,
    private val basketProductRepository: IBasketProductRepository
) :ViewModel() {

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


}
