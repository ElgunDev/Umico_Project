package com.matrix.android105_android.presentation.ui.Login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.matrix.android105_android.domain.UseCase.Login.ILoginUseCase
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase,
    private val firebaseAuth: FirebaseAuth
):ViewModel() {

    private val _selectedLocale = MutableLiveData<Locale>()
    val selectedLocale:MutableLiveData<Locale>
        get() = _selectedLocale

    private val _verificationState = MutableLiveData<NetworkResource<Boolean>>()
    val verificationState:MutableLiveData<NetworkResource<Boolean>>
        get()=_verificationState

    private val _verificationId = MutableLiveData<NetworkResource<String>>()
    val verificationId:MutableLiveData<NetworkResource<String>>
        get()=_verificationId


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

     private val callback = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(creditial: PhoneAuthCredential) {
            verifyCodeAndLogin(creditial)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            _verificationState.postValue(NetworkResource.Error(e.message ?: "Verification failed"))
           _verificationId.postValue(NetworkResource.Error(e.message?:"Verification failed"))

        }
        override fun onCodeSent(veritificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            _verificationId.value= NetworkResource.Success(veritificationId)
        }
    }

    fun  sendVerificationCode(phoneNumber:String ){
        viewModelScope.launch {
               loginUseCase.sendVerificationCode(phoneNumber,callback)
           }

    }
    fun verifyCodeAndLogin(credential: PhoneAuthCredential){
        viewModelScope.launch {
            val success = loginUseCase.verificationCodeAndLogin(credential)
            _verificationState.value =NetworkResource.Success(success)
        }
    }

}