package com.matrix.android105_android.presentation.ui.likedProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.matrix.android105_android.databinding.FragmentUmicoMarketBinding
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UmicoMarketFragment : Fragment() {
    private lateinit var binding:FragmentUmicoMarketBinding
    private lateinit var likedAdapter:LikedProductAdapter


    private val umicoMarketViewModel :UmicoMarketViewModel by viewModels()

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
        umicoMarketViewModel.fetchLikedProducts()
    }

    private fun setUmicoMarketAdapter(){
        likedAdapter = LikedProductAdapter()
        binding.rcyUmicoMarket.adapter = likedAdapter
        binding.rcyUmicoMarket.layoutManager = GridLayoutManager(context ,2)
        umicoMarketViewModel.likedProducts.observe(viewLifecycleOwner){resource->
            when(resource) {
                is NetworkResource.Success -> {
                    val products = resource.data
                    showProgressBar(false)
                    if (products.isNullOrEmpty()) {
                        binding.rcyUmicoMarket.visibility = View.GONE
                        binding.imgLike.visibility = View.VISIBLE
                        binding.txtNoProduct.visibility = View.VISIBLE
                        binding.txtAddList.visibility = View.VISIBLE
                    } else {
                        binding.rcyUmicoMarket.visibility = View.VISIBLE
                        binding.imgLike.visibility = View.GONE
                        binding.txtAddList.visibility = View.GONE
                        binding.txtNoProduct.visibility = View.GONE
                        likedAdapter.submitList(products)
                    }
                }
                is NetworkResource.Error->{
                    Toast.makeText(requireContext(),resource.message , Toast.LENGTH_SHORT).show()
                    showProgressBar(false)
                }
                is NetworkResource.Loading->{
                    showProgressBar(true)
                }

            }
        }

    }
    private fun showProgressBar(show:Boolean){
        if (show){
            binding.progressBar3.visibility = View.VISIBLE
            binding.rcyUmicoMarket.visibility = View.GONE
            binding.imgLike.visibility = View.GONE
            binding.txtNoProduct.visibility = View.GONE
            binding.txtAddList.visibility = View.GONE
        }
        else{
            binding.progressBar3.visibility = View.INVISIBLE
        }

    }
}