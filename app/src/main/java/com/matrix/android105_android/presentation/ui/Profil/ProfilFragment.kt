package com.matrix.android105_android.presentation.ui.Profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentProfilBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilFragment : Fragment() {
    private lateinit var binding:FragmentProfilBinding
    private val profilViewModel:ProfilViewModel by viewModels()


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
        observeUserName()
        super.onViewCreated(view, savedInstanceState)
    }

   private fun observeUserName(){
        profilViewModel.userName.observe(viewLifecycleOwner){name->
            binding.txtUser.text = name
            binding.btnProfilText.text = name.first().toString().uppercase()

        }
    }


}