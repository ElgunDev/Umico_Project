package com.matrix.android105_android.presentation.ui.Login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentLogInBinding
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class LogInFragment : Fragment() {
    lateinit var binding: FragmentLogInBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickButtonColor()

        observeVerificationId()
        click()
        loginViewModel.selectedLocale.observe(viewLifecycleOwner){
            setLocale(it)
            updateUITexts()
        }

        binding.CCPSelectLanguage.setOnCountryChangeListener {
            loginViewModel.updateLocaleCountry(binding.CCPSelectLanguage.selectedCountryNameCode)
        }
        click()
    }

    private fun observeVerificationId(){
        loginViewModel.verificationId.observe(viewLifecycleOwner){resource->
            when(resource) {
                is NetworkResource.Success ->{
                    val verificationId = resource.data
                    val action =
                        LogInFragmentDirections.actionLogInFragmentToVerificationCodeFragment(
                            verificationId = verificationId
                        )
                    findNavController().navigate(action)
                    binding.progressBar.visibility =View.INVISIBLE
                }
                is NetworkResource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResource.Error ->{
                    Toast.makeText(requireContext(),resource.message , Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setLocale(locale:Locale){
        val resources = resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration,resources.displayMetrics)
    }
    private fun updateUITexts() {
        binding.txtInfromation.text = getString(R.string.Information)
        binding.btnLogin.text = getString(R.string.LogIn)
        binding.txtPhoneNumberText.text = getString(R.string.PhoneNumber)
        binding.txtEnterPhoneNumber.text = getString(R.string.enterPhoneNumber)
        binding.edtPhoneNumber.hint = getString(R.string.PhoneNumber)
    }


    fun click(){
        binding.btnLogin.setOnClickListener(){
            val phoneNumber ="+994"+ binding.edtPhoneNumber.text.toString()
            binding.progressBar.visibility = View.VISIBLE
            loginViewModel.sendVerificationCode(phoneNumber)

        }
    }

    fun setUpClickButtonColor(){
        binding.edtPhoneNumber.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! >= 7){
                    binding.btnLogin.apply {
                        backgroundTintList = ContextCompat.getColorStateList(requireContext() , R.color.Purple)
                        isEnabled = true
                    }
                }
                else{
                    binding.btnLogin.apply {
                        backgroundTintList = ContextCompat.getColorStateList(requireContext() , R.color.lightGray)
                        isEnabled = false
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }






}