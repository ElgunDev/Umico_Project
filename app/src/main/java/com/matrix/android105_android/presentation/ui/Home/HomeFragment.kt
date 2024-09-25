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
    private lateinit var adAdapter: AdAdapter
    private lateinit var shopAdapter:ShopsAdapter
    private lateinit var productAdapter:ProductAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter

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
        homeViewModel.fetchShops()
        homeViewModel.startCountDown()
        homeViewModel.fetchProducts()
        homeViewModel.fetchRecommendationProducts()
        observeUserName()
        setAdAdapter()
        setShopAdapter()
        observeTimer()
        setProductAdapter()
        setRecommendationAdapter()

    }

    private fun setAdAdapter(){
        adAdapter = AdAdapter()
        binding.rcyAdvertising.adapter = adAdapter
        binding.rcyAdvertising.layoutManager  = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        homeViewModel.adImages.observe(viewLifecycleOwner){adImages->
            adAdapter.submitList(adImages)
        }
    }
    private fun setShopAdapter(){
        shopAdapter = ShopsAdapter()
        binding.rcyMarkets.adapter = shopAdapter
        binding.rcyMarkets.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        homeViewModel.shops.observe(viewLifecycleOwner){
            shopAdapter.submitList(it)
        }
    }
    private fun setProductAdapter(){
      productAdapter = ProductAdapter()
        binding.rcyDiscountedproducts.adapter = productAdapter
        binding.rcyDiscountedproducts.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL,false)
        homeViewModel.product.observe(viewLifecycleOwner){productList->
            productAdapter.submitList(productList)
        }
    }

    private fun setRecommendationAdapter(){
        recommendationAdapter = RecommendationAdapter()
        binding.rcyRecomendations.adapter = recommendationAdapter
        binding.rcyRecomendations.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL,false)
       homeViewModel.recommendationProducts.observe(viewLifecycleOwner){recommendationList->
           recommendationAdapter.submitList(recommendationList)
       }
    }


    private fun observeUserName(){
        homeViewModel.username.observe(viewLifecycleOwner){name->
            binding.txtName.text = name
            binding.btnProfilText.text = name.first().toString().uppercase()
        }
    }

    private fun observeTimer(){
        homeViewModel.timeRemaining.observe(viewLifecycleOwner){time->
            binding.timer.text = time
        }
    }
}