package com.matrix.android105_android.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor():ViewModel() {

    private val _languageCode  = MutableLiveData<String>()
    val languageCode :MutableLiveData<String>
        get() = _languageCode

    fun setLanguage(language:String){
        _languageCode.value = language
    }
}