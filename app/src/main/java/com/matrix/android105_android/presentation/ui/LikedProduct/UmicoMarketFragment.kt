package com.matrix.android105_android.presentation.ui.LikedProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentUmicoMarketBinding
import com.matrix.android105_android.presentation.ui.Home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UmicoMarketFragment : Fragment() {
    private lateinit var binding:FragmentUmicoMarketBinding
    private lateinit var likedAdapter:LikedProductAdapter

    private val homeViewModel :HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUmicoMarketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUmicoMarketAdapter()
    }

    private fun setUmicoMarketAdapter(){
        likedAdapter = LikedProductAdapter()
        binding.rcyUmicoMarket.adapter = likedAdapter
        binding.rcyUmicoMarket.layoutManager = GridLayoutManager(context ,2)
        homeViewModel.likedProduct.observe(viewLifecycleOwner){
            if (it.isNullOrEmpty()) {
                binding.rcyUmicoMarket.visibility = View.GONE
                binding.emptyStateView.visibility = View.VISIBLE
            } else {
                binding.rcyUmicoMarket.visibility = View.VISIBLE
                binding.emptyStateView.visibility = View.GONE
                likedAdapter.submitList(it)
            }
        }

    }
}