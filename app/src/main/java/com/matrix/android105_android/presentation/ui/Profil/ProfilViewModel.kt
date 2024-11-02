package com.matrix.android105_android.presentation.ui.Profil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.matrix.android105_android.domain.UseCase.Profil.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(
    private val userNameUseCase: GetUserNameUseCase,
    private val firebaseAuth: FirebaseAuth
):ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName:MutableLiveData<String>
        get() = _userName


    fun fetchUserName(){
        val uId =firebaseAuth.currentUser?.uid
        if (uId!=null){
            viewModelScope.launch {
                val result = userNameUseCase.invoke(uId)
                result.onSuccess {name->
                    _userName.value = name
                }
                result.onFailure {exception->
                    _userName.value = "Error : ${exception.message}"
                }
            }
        }
    }
}