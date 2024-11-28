package com.matrix.android105_android.presentation.ui.MoreProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentMoreProductBinding
import com.matrix.android105_android.presentation.ui.Home.AllProductAdapter
import com.matrix.android105_android.presentation.ui.Home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreProductFragment : Fragment() {
    private lateinit var binding:FragmentMoreProductBinding
    private lateinit var moreProductAdapter: MoreProductAdapter

    private val moreProductViewModel:MoreProductViewModel by viewModels()
    private val homeViewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moreProductViewModel.fetchMoreProduct()
        setMoreAdapter()
        click()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setMoreAdapter(){
        moreProductAdapter = MoreProductAdapter(
            addProduct = {item , notify->
                homeViewModel.addProductToLikes(item , notify)
            },
            deleteProduct = {item , notify->
                homeViewModel.removeLikedProduct(item , notify)
            },
            homeViewModel=homeViewModel,
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item,notfy->
                homeViewModel.deleteProductToBasket(item,notfy)
            }
        )
        binding.rcyMoreProducts.adapter =moreProductAdapter
        binding.rcyMoreProducts.layoutManager =GridLayoutManager(context ,2)
        moreProductViewModel.moreProduct.observe(viewLifecycleOwner){
            moreProductAdapter.submitList(it)
        }
    }
    private fun click(){
        binding.backButton.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.btnLike.setOnClickListener(){
            findNavController().navigate(R.id.action_moreProductFragment_to_likedProductFragment)
        }
        binding.imgBasket.setOnClickListener(){
            findNavController().navigate(R.id.action_moreProductFragment_to_basketProductFragment)
        }
    }
}