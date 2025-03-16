package com.matrix.android105_android.presentation.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentServicesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ServicesFragment : Fragment() {
    private lateinit var binding: FragmentServicesBinding
    private lateinit var shopAdapter: ShopAdapter
    private lateinit var adViewPager: AdViewPager

    private val serviceViewModel:ServiceViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        serviceViewModel.fetchShops()
        setShopAdapter()
        setViewPager()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setShopAdapter(){
        shopAdapter = ShopAdapter()
        binding.rcyShops.adapter = shopAdapter
        binding.rcyShops.layoutManager = GridLayoutManager(context,4)
        serviceViewModel.shops.observe(viewLifecycleOwner){
            shopAdapter.submitList(it)
        }

    }

    private fun setViewPager(){
        val adLIst = listOf(
            Advertisements(R.drawable.ad_images),
            Advertisements(R.drawable.ad_images_second)
        )
        adViewPager =AdViewPager(adLIst)
        binding.viewPager.adapter = adViewPager
        binding.dots.attachTo(binding.viewPager)
    }




}