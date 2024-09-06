package com.matrix.android105_android.presentation.Login

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.matrix.android105_android.domain.UseCase.ILoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase,
    private val firebaseAuth: FirebaseAuth
):ViewModel() {

    private val _selectedLocale = MutableLiveData<Locale>()
    val selectedLocale:MutableLiveData<Locale>
        get() = _selectedLocale

    private val _verificationState = MutableLiveData<Boolean>()
    val verificationState:MutableLiveData<Boolean>
        get()=_verificationState

    private val _verificationId = MutableLiveData<String>()
    val verificationId:MutableLiveData<String>
        get()=_verificationId

    private val _verificationError = MutableLiveData<String>()
    val verificationError :MutableLiveData<String>
        get() = _verificationError

     fun updateLocaleCountry(countryCode: String){
        val locale = getLocaleForCountry(countryCode)
        _selectedLocale.value= locale
    }

     private val callback = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(creditial: PhoneAuthCredential) {
            verifyCodeAndLogin(creditial)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            _verificationState.postValue(false)
            _verificationError.postValue(e.message)

        }
        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            _verificationId.value= verificationId
            _verificationError.postValue("Code sent")
        }
    }
    private fun getLocaleForCountry(countryCode:String):Locale{
        return when(countryCode){
            "US" -> Locale("en" , "US")
            "AZ" -> Locale("az" , "AZ")
            else -> Locale.getDefault()
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
            _verificationState.value =success
        }
    }

}