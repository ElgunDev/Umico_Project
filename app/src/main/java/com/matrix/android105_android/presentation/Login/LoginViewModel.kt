package com.matrix.android105_android.presentation.Login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.matrix.android105_android.domain.UseCase.ILoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase
):ViewModel() {

    private val _selectedLocale = MutableLiveData<Locale>()
    val selectedLocale:MutableLiveData<Locale>
        get() = _selectedLocale

    private val _verificationState = MutableLiveData<Boolean>()
    val verificationState:MutableLiveData<Boolean>
        get()=_verificationState
     fun updateLocaleCountry(countryCode: String){
        val locale = getLocaleForCountry(countryCode)
        _selectedLocale.value= locale
    }
    private fun getLocaleForCountry(countryCode:String):Locale{
        return when(countryCode){
            "US" -> Locale("en" , "US")
            "AZ" -> Locale("az" , "AZ")
            else -> Locale.getDefault()
        }
    }

    fun  sendVerificationCode(phoneNumber:String , callbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks){
        viewModelScope.launch {
            loginUseCase.sendVerificationCode(phoneNumber,callbacks)
        }
    }
    fun verifyCodeAndLogin(credential: PhoneAuthCredential){
        viewModelScope.launch {
            val success = loginUseCase.verificationCodeAndLogin(credential)
            _verificationState.value =success
        }
    }
}