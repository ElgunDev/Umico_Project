package com.matrix.android105_android.presentation.ui.profilDetailed

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.matrix.android105_android.domain.useCase.profil.GetProfilImagesUseCase
import com.matrix.android105_android.domain.useCase.profil.UploadProfilImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilDetailedViewModel @Inject constructor(
    private val getProfilImagesUseCase: GetProfilImagesUseCase,
    private val uploadProfilImagesUseCase: UploadProfilImagesUseCase,
    private val firebaseAuth: FirebaseAuth
):ViewModel(){
      private val _selectedProfilImage = MutableLiveData<String?>()
    val selectedProfilImage : MutableLiveData<String?>
        get() = _selectedProfilImage

    private val _imageVisibility = MutableLiveData<Int>()
    val imageVisibility: LiveData<Int>
        get() = _imageVisibility

    private val _containerVisibility = MutableLiveData<Int>()
    val containerVisibility : LiveData<Int>
        get() = _containerVisibility



    fun uploadImages(images:Any){
        viewModelScope.launch {
            val uid  = firebaseAuth.currentUser?.uid
            val result = uid?.let { uploadProfilImagesUseCase.invoke(images, it) }
            if (result != null && result.isSuccess) {
                loadProfileImage()
            }
        }

    }

    fun loadProfileImage() {
        viewModelScope.launch {
            val uid = firebaseAuth.currentUser?.uid
            val result = uid?.let { getProfilImagesUseCase.invoke(uid) }
                   result?.onSuccess {url->
                       _selectedProfilImage.value = url
                       _imageVisibility.value = View.VISIBLE
                       _containerVisibility.value =View.GONE
                   }
                       ?.onFailure {
                           _selectedProfilImage.value ="error: ${it.message}"
                           _imageVisibility.value = View.GONE
                           _containerVisibility.value = View.VISIBLE
                    }

        }
    }
}

