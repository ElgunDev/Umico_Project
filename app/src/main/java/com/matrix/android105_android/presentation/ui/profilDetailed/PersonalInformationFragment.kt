package com.matrix.android105_android.presentation.ui.profilDetailed

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.matrix.android105_android.data.network.fireBase.Repository.profil.PersonalInformations
import com.matrix.android105_android.databinding.FragmentPersonalInformationBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class PersonalInformationFragment : Fragment() {
    lateinit var binding: FragmentPersonalInformationBinding
    private val personalInformationViewModel:PersonalInformationViewModel by activityViewModels()
    private val profilDetailedViewModel:ProfilDetailedViewModel by activityViewModels()
    private var profilImages:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentPersonalInformationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        click()
        personalInformationViewModel.getPersonalInformations()
        updatePersonalInformation()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun click(){
        binding.imgBackButton.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.dateOfBirthText.setOnClickListener(){
          showDatePicker()
        }
        binding.genderText.setOnClickListener(){
            showGenderSelectionDialog()
        }
    }

    private fun observe(){
        personalInformationViewModel.personalInformation.observe(viewLifecycleOwner){
            binding.nameInputText.setText(it?.name)
            binding.surnameInputText.setText(it?.surName)
            binding.PhoneNumberText.setText(it?.phoneNumber)
            binding.emailText.setText(it?.email)
            binding.FinText.setText(it?.fin)
            binding.dateOfBirthText.setText(it?.brithDay)
            binding.genderText.setText(it?.gender)
        }
        personalInformationViewModel.selectedGender.observe(viewLifecycleOwner){
            binding.genderText.setText(it)
        }
        personalInformationViewModel.selectedDate.observe(viewLifecycleOwner){
            binding.dateOfBirthText.setText(it)
        }
    }
    private fun updatePersonalInformation(){
        binding.btnSave.setOnClickListener(){
            profilDetailedViewModel.selectedProfilImage.observe(viewLifecycleOwner){
                it?.let {
                    if (it.isNotBlank()) {
                     profilImages=it
                    }
                }
            }
            val updateInfo = PersonalInformations(
                name = binding.nameInputText.text.toString(),
                surName = binding.surnameInputText.text.toString(),
                phoneNumber = binding.PhoneNumberText.text.toString(),
                email = binding.emailText.text.toString(),
                fin = binding.FinText.text.toString(),
                brithDay = personalInformationViewModel.selectedDate.value ?: "N/A",
                gender = personalInformationViewModel.selectedGender.value ?: "",
                profileImageUrl = profilImages
            )
            personalInformationViewModel.updatePersonalInformations(updateInfo)
            findNavController().popBackStack()
        }
    }

    private fun showGenderSelectionDialog() {
        val genderOptions = arrayOf("Kişi", "Qadın","Qeyd etmək istəmirəm")
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Cinsi Seçin")
            .setSingleChoiceItems(genderOptions, -1) { dialog, which ->
                val selectedGender = genderOptions[which]
                personalInformationViewModel.updateGender(selectedGender)
            }
            .setPositiveButton("Seç") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("İmtina et") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }

        private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                personalInformationViewModel.updateSelectedDate("$selectedDay/${selectedMonth + 1}/$selectedYear")
            },
            year, month, day
        ).show()
    }

}