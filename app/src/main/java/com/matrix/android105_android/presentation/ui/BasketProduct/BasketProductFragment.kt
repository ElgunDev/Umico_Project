package com.matrix.android105_android.presentation.ui.BasketProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentBasketProductBinding
import com.matrix.android105_android.presentation.ui.Home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketProductFragment : Fragment() {

    private lateinit var binding:FragmentBasketProductBinding
    private lateinit var adapter: BasketProductAdapter
    private val homeViewModel :HomeViewModel by viewModels()
    private val basketViewModel:BasketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        clickBackButton()

    }

    private fun setUpAdapter(){
        adapter = BasketProductAdapter(
            increaseClick = {productId , currentStock ->
                basketViewModel.increaseProductStock(productId,currentStock)
            },
            decreaseClick = {productId , currentStock ->
                basketViewModel.decreaseProductStock(productId,currentStock)
            },
            updateQuantity = {productId , newQuantity->
                basketViewModel.updateProductQuantity(productId , newQuantity)
            }
        )
        binding.rcyProducts.adapter = adapter
        binding.rcyProducts.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        homeViewModel.basketProduct.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        adapter.updateFragmentTotalPrice ={
        updateTotalPrice()
        }
        adapter.updateFragmentDiscountPrice={
            updateDiscountPrice()
        }
        adapter.updateFragmentLastPrice={
            updateLastPrice()
        }

    }
    private fun updateTotalPrice() {
        val totalPrice = adapter.getTotalPriceForItems()
        binding.OrderAmountValue.text = totalPrice.toString()
    }
    private fun updateDiscountPrice(){
        val discountPrice = adapter.getDiscountPrice()
        binding.DiscountValue.text = discountPrice.toString()
    }

    private fun updateLastPrice(){
        val lastPrice = adapter.getLastPrice()
        binding.TotalPayment.text = lastPrice.toString()
    }

    private fun clickBackButton(){
        binding.imgBackButton.setOnClickListener(){
            findNavController().popBackStack()
        }
    }







}