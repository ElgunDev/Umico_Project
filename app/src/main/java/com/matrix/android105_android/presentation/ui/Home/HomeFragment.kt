package com.matrix.android105_android.presentation.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: AdAdapter
    private val homeViewModel: HomeViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.fetchUserName()
        homeViewModel.fetchAdImages()
        observeUserName()
        setAdAdapter()
    }

    private fun setAdAdapter(){
        adapter = AdAdapter()
        binding.rcyAdvertising.adapter = adapter
        binding.rcyAdvertising.layoutManager  = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        homeViewModel.adImages.observe(viewLifecycleOwner){adImages->
            adapter.submitList(adImages)
        }
    }

    private fun observeUserName(){
        homeViewModel.username.observe(viewLifecycleOwner){name->
            binding.txtName.text = name
            binding.btnProfilText.text = name.first().toString().uppercase()
        }
    }
}