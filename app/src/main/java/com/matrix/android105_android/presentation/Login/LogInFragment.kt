package com.matrix.android105_android.presentation.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class LogInFragment : Fragment() {
    lateinit var binding: FragmentLogInBinding
    private val loginViewModel:LoginViewModel by viewModels()
    private lateinit var verificationId: String

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
       click()
        observe()
        loginViewModel.selectedLocale.observe(viewLifecycleOwner){
            setLocale(it)
            updateUITexts()
        }

        binding.CCPSelectLanguage.setOnCountryChangeListener {
            loginViewModel.updateLocaleCountry(binding.CCPSelectLanguage.selectedCountryNameCode)
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
            val phoneNumber = binding.edtPhoneNumber.text.toString()
            loginViewModel.sendVerificationCode(phoneNumber,callback)
            val action = LogInFragmentDirections.actionLogInFragmentToVerificationCodeFragment(verificationId)
            findNavController().navigate(action)
        }
    }

    private val callback = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(creditial: PhoneAuthCredential) {
            loginViewModel.verifyCodeAndLogin(creditial)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(context, "Verification Failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            this@LogInFragment.verificationId= verificationId
            Toast.makeText(context, "Code Sent", Toast.LENGTH_SHORT).show()
        }

    }

    private fun observe(){
        loginViewModel.verificationState.observe(viewLifecycleOwner){success->
            if (success){
              findNavController().navigate(R.id.action_verificationCodeFragment_to_mainFragment)
            }
            else {
                // Handle failure
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}