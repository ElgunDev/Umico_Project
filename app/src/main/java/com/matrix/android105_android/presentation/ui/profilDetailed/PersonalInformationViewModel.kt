package com.matrix.android105_android.presentation.ui.profilDetailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.matrix.android105_android.data.network.fireBase.Repository.profil.PersonalInformations
import com.matrix.android105_android.domain.useCase.profil.GetPersonalInformationUseCase
import com.matrix.android105_android.domain.useCase.profil.UpdatePersonalInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInformationViewModel @Inject constructor(
    private val getPersonalInformationUseCase: GetPersonalInformationUseCase,
    private val updatePersonalInformationUseCase: UpdatePersonalInformationUseCase,
    private val firebaseAuth: FirebaseAuth
):ViewModel() {

    private val _personalInformation = MutableLiveData<PersonalInformations?>()
    val personalInformation :LiveData<PersonalInformations?>
        get()= _personalInformation



    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    private val _selectedGender = MutableLiveData<String>()
    val selectedGender: LiveData<String> get() = _selectedGender

    fun updateGender(newGender: String) {
        _selectedGender.value = newGender
    }

    fun updateSelectedDate(newDate: String) {
        _selectedDate.value = newDate
    }

    fun getPersonalInformations(){
        viewModelScope.launch {
            val uid = firebaseAuth.currentUser?.uid
            val result = uid?.let { getPersonalInformationUseCase.invoke(uid) }
            result?.onSuccess {
                _personalInformation.postValue(it)
            }
        }
    }

    fun updatePersonalInformations(personalInformations: PersonalInformations){
        viewModelScope.launch{
            val uid = firebaseAuth.currentUser?.uid
            val result =uid?.let { updatePersonalInformationUseCase.invoke(uid,personalInformations) }
            result?.onSuccess {
                _personalInformation.postValue(personalInformations)
            }
        }
    }

}