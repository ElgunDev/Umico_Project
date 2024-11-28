package com.matrix.android105_android.presentation.ui.bonus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.android105_android.data.Network.fireBase.Repository.Bonus.Partners.Partners
import com.matrix.android105_android.domain.UseCase.Bonus.Partners.PartnersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusViewModel @Inject constructor(
    private val partnersUseCase: PartnersUseCase
):ViewModel() {
    private val _partnersImages = MutableLiveData<List<Partners>>()
    val partnersImages :LiveData<List<Partners>>
        get() = _partnersImages


    fun fetchPartnersImages(){
        viewModelScope.launch {
            try {
                val images = partnersUseCase.getPartners()
                _partnersImages.value = images
            }
            catch (e:Exception){

            }
        }
    }
}