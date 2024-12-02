package com.matrix.android105_android.presentation.ui.Profil

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentProfilBinding
import com.matrix.android105_android.myapplication.MainActivity
import com.matrix.android105_android.presentation.ui.Login.LoginViewModel
import com.matrix.android105_android.presentation.ui.ProfilDetailed.ProfilDetailedViewModel
import com.matrix.android105_android.tools.LanguageEnum
import com.matrix.android105_android.tools.LanguageEnum.Companion.lowercase
import com.matrix.android105_android.tools.SessionManager

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilFragment : Fragment() {
    private lateinit var binding:FragmentProfilBinding
    private val profilViewModel:ProfilViewModel by viewModels()
    private val profilDetailedViewModel:ProfilDetailedViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profilViewModel.fetchUserName()
        profilDetailedViewModel.loadProfileImage()
        observeUserName()
        click()
        observes()
        bindingText()
        super.onViewCreated(view, savedInstanceState)
    }

   private fun observeUserName(){
        profilViewModel.userName.observe(viewLifecycleOwner){name->
            try {
                binding.txtUser.text = name
                binding.btnProfilText.text = name.first().toString().uppercase()
            }
            catch (e:Exception){
                binding.txtUser.text =""
                binding.btnProfilText.text =""
            }
        }
    }
    private fun click(){
        binding.btnLike.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_likedProductFragment)
        }
        binding.imgBasket.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_basketProductFragment)
        }
        binding.layoutProfil.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_profilDetailedFragment)
        }
        binding.layoutLanguage.setOnClickListener(){
            showLanguageDialog()
        }
        binding.layoutExit.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_logInFragment)
        }

    }

    private fun observes(){
        profilDetailedViewModel.selectedProfilImage.observe(viewLifecycleOwner){
            it?.let {
                if (it.isNotBlank()) {
                    profilDetailedViewModel.loadProfileImage()
                    Glide.with(this).load(it).into(binding.SelectedImage)
                    binding.SelectedImage.visibility = View.VISIBLE

                } else {
                    binding.SelectedImage.visibility = View.GONE
                }
            }
        }
        profilDetailedViewModel.imageVisibility.observe(viewLifecycleOwner){
            binding.SelectedImage.visibility =it
        }
        profilDetailedViewModel.containerVisibility.observe(viewLifecycleOwner){
            binding.btnProfilText.visibility =it
        }
    }

    private fun showLanguageDialog(){
        val language = arrayOf(getString(R.string.english ), getString(R.string.azerbaijan))
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.select_language))
            .setItems(language){_,which->
                when(which){
                    0->{
                        (activity as MainActivity).changeLanguage(LanguageEnum.EN)
                    }
                    1-> {
                        (activity as MainActivity).changeLanguage(LanguageEnum.AZ)
                    }
                }
            }
            .show()

    }
    fun bindingText(){
        binding.txtLanguageSecond.text = SessionManager.language.fullName
    }



}