package com.matrix.android105_android.presentation.ui.verificationCode

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
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.PhoneAuthProvider
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentVerificationCodeBinding
import com.matrix.android105_android.presentation.ui.Login.LoginViewModel
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationCodeFragment : Fragment() {
    private lateinit var binding: FragmentVerificationCodeBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val args: VerificationCodeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerificationCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        click()
        setupEditTexts()
    }


    private fun setupEditTexts() {
        val editTexts = listOf(
            binding.edtCode1,
            binding.edtCode2,
            binding.edtCode3,
            binding.edtCode4,
            binding.edtCode5,
            binding.edtCode6
        )

        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        editTexts[i].background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_edit_text_filled
                        )
                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        }
                    } else {
                        editTexts[i].background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_edit_text_code
                        )
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            editTexts[i].setOnKeyListener { v, keyCode, event ->
                if (event.action == android.view.KeyEvent.ACTION_DOWN && keyCode == android.view.KeyEvent.KEYCODE_DEL && i > 0 && editTexts[i].text.isEmpty()) {
                    editTexts[i - 1].requestFocus()
                }
                false
            }
        }
    }

    private fun click() {
        binding.btnVerifityCode.setOnClickListener {
            val verificationId = args.verificationId
            val code = getCodeFromEditTexts()
            val creditial = PhoneAuthProvider.getCredential(verificationId, code)
           showProgressBar(true)
            loginViewModel.verifyCodeAndLogin(creditial)
        }
    }

    private fun showProgressBar(show:Boolean){
        if (show){
            binding.progressBar2.visibility = View.VISIBLE
            binding.btnVerifityCode.text = ""
        }
        else{
            binding.progressBar2.visibility =View.INVISIBLE
            binding.btnVerifityCode.text = getString(R.string.Verifity)
        }
    }

    private fun getCodeFromEditTexts(): String {
        val code1 = binding.edtCode1.text.toString()
        val code2 = binding.edtCode2.text.toString()
        val code3 = binding.edtCode3.text.toString()
        val code4 = binding.edtCode4.text.toString()
        val code5 = binding.edtCode5.text.toString()
        val code6 = binding.edtCode6.text.toString()
        return code1 + code2 + code3 + code4 + code5 + code6
    }

    private fun observe() {
        loginViewModel.verificationState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is NetworkResource.Success -> {
                    if (resource.data) {
                        findNavController().navigate(R.id.action_verificationCodeFragment_to_mainFragment)
                    } else {
                        Toast.makeText(context, "Verification failed", Toast.LENGTH_SHORT).show()
                    }
                   showProgressBar(false)
                }
                is NetworkResource.Error ->{
                    Toast.makeText(context, "Login Failed: ${resource.message}", Toast.LENGTH_SHORT).show()
                    showProgressBar(false)
                }
                is NetworkResource.Loading->{
                   showProgressBar(true)
                }
            }

        }


    }
}
