package com.matrix.android105_android.presentation.ui.Profil

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentProfilBinding
import com.matrix.android105_android.presentation.ui.ProfilDetailed.ProfilDetailedViewModel
import com.matrix.android105_android.presentation.ui.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilFragment : Fragment() {
    private lateinit var binding:FragmentProfilBinding
    private val profilViewModel:ProfilViewModel by viewModels()
    private val profilDetailedViewModel:ProfilDetailedViewModel by viewModels()
    private val sharedViewModel:SharedViewModel by activityViewModels()


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
                    0->sharedViewModel.setLanguage("en")
                    1->sharedViewModel.setLanguage("az")
                }

            }
            .show()

    }


}