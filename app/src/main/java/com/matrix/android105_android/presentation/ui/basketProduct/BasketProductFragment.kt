package com.matrix.android105_android.presentation.ui.basketProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentBasketProductBinding
import com.matrix.android105_android.presentation.ui.home.HistoryAdapter
import com.matrix.android105_android.presentation.ui.home.HomeViewModel
import com.matrix.android105_android.presentation.ui.home.RecommendationAdapter
import com.matrix.android105_android.presentation.ui.shop.DiscountAdapter
import com.matrix.android105_android.presentation.ui.shop.ShopViewModel
import com.matrix.android105_android.presentation.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketProductFragment : Fragment() {

    private lateinit var binding:FragmentBasketProductBinding
    private lateinit var adapter: BasketProductAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var recommendationsAdapter: RecommendationAdapter
    private lateinit var discountAdapter: DiscountAdapter
    private val homeViewModel :HomeViewModel by activityViewModels()
    private val basketViewModel:BasketViewModel by viewModels()
    private val shopViewModel :ShopViewModel by  viewModels()

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
        clickMore()
        fetchHistoryAdapter()
        fetchDiscountAdapter()
        fetchRecommendationAdapter()
        clickCatalog()
        homeViewModel.fetchHistoryProducts()
        homeViewModel.fetchRecommendationProducts()
        shopViewModel.fetchProducts()
        basketViewModel.fetchBasketProduct()
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
        basketViewModel.basketProduct.observe(viewLifecycleOwner){resource->
            when(resource) {
                is NetworkResource.Success-> {
                    val product = resource.data
                    showProgressBar(false)
                    if (product.isNullOrEmpty()) {
                        binding.scrollFirst.visibility = View.GONE
                        binding.layoutBottom.visibility = View.GONE
                        binding.scrollSecond.visibility = View.VISIBLE
                    } else {
                        binding.scrollFirst.visibility = View.VISIBLE
                        binding.layoutBottom.visibility = View.VISIBLE
                        binding.scrollSecond.visibility = View.GONE
                        adapter.submitList(product)
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
   private fun showProgressBar(show:Boolean){
        if (show){
            binding.progressBar4.visibility = View.VISIBLE
            binding.scrollFirst.visibility = View.GONE
            binding.layoutBottom.visibility = View.GONE
            binding.scrollSecond.visibility = View.GONE
        }
        else{
            binding.progressBar4.visibility = View.INVISIBLE
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

    private fun fetchHistoryAdapter(){
        historyAdapter = HistoryAdapter(
            addProduct = {item , notify ->
                homeViewModel.addProductToLikes(item , notify)
            },
            deleteProduct = {item, notify->
                homeViewModel.removeLikedProduct(item , notify)
            },
            homeViewModel = homeViewModel,
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item , notify->
                homeViewModel.deleteProductToBasket(item,notify)
            },
            onClick = {
               findNavController().navigate(R.id.action_basketProductFragment_to_moreProductFragment)
            }
        )
        binding.rcyHistory.adapter= historyAdapter
        binding.rcyHistory.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        homeViewModel.historyProduct.observe(viewLifecycleOwner){
            historyAdapter.submitList(it)
        }
    }

    private fun fetchRecommendationAdapter(){
        recommendationsAdapter = RecommendationAdapter(

            addProduct = {item , notify ->
                homeViewModel.addProductToLikes(item , notify)
            },
            deleteProduct = {item, notify->
                homeViewModel.removeLikedProduct(item , notify)
            },
           homeViewModel=homeViewModel,
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item , notify->
                homeViewModel.deleteProductToBasket(item,notify)
            },
            onClick = {
                findNavController().navigate(R.id.action_basketProductFragment_to_moreProductFragment)
            }
        )
        binding.rcyRecommendation.adapter = recommendationsAdapter
        binding.rcyRecommendation.layoutManager =LinearLayoutManager(requireContext() ,LinearLayoutManager.HORIZONTAL , false)
        homeViewModel.recommendationProducts.observe(viewLifecycleOwner){
            recommendationsAdapter.submitList(it)
        }

    }

    private fun fetchDiscountAdapter(){
        discountAdapter = DiscountAdapter(

            addProduct = {item , notify ->
                homeViewModel.addProductToLikes(item , notify)
            },
            deleteProduct = {item, notify->
                homeViewModel.removeLikedProduct(item , notify)
            },

            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            homeViewModel = homeViewModel,
            deleteProductBasket = {item , notify->
                homeViewModel.deleteProductToBasket(item,notify)
            }
        )
        binding.rcyDiscount.adapter = discountAdapter
        binding.rcyDiscount.layoutManager =LinearLayoutManager(requireContext() ,LinearLayoutManager.HORIZONTAL , false)
        shopViewModel.products.observe(viewLifecycleOwner){
            discountAdapter.submitList(it)
        }

    }

    private fun clickMore(){
        binding.more.setOnClickListener(){
            findNavController().navigate(R.id.action_basketProductFragment_to_moreProductFragment)
        }
    }
    private fun clickCatalog(){
        binding.btnCatalog.setOnClickListener(){
            findNavController().navigate(R.id.action_basketProductFragment_to_catalogFragment)
        }
    }


}