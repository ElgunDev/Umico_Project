package com.matrix.android105_android.presentation.ui.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.Popular
import com.matrix.android105_android.domain.useCase.home.popular.PopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase
):ViewModel() {
    private val _popularItems  = MutableLiveData<List<Popular>>()
    val popularItems : MutableLiveData<List<Popular>>
        get() = _popularItems

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
}