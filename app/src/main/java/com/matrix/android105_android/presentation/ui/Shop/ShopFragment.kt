package com.matrix.android105_android.presentation.ui.Shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentShopBinding
import com.matrix.android105_android.presentation.ui.Home.HomeViewModel
import com.matrix.android105_android.presentation.ui.Home.RecommendationAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var binding:FragmentShopBinding
    private lateinit var catalogAdapter: CatalogAdapter
    private lateinit var discountAdapter: DiscountAdapter
    private lateinit var brandsAdapter: BrandsAdapter
    private lateinit var sellerAdapter: SellerAdapter
    private lateinit var recommendationsAdapter: RecommendationAdapter
    private lateinit var innovationAdapter: InnovationAdapter

    private val shopViewModel : ShopViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCatalogAdapter()
        setDiscountProduct()
        setBrandsAdapter()
        setSellerAdapter()
        setRecommendationProduct()
        setInnovationProduct()
        click()
        shopViewModel.fetchPopularItems()
        shopViewModel.fetchProducts()
        shopViewModel.fetchBrands()
        shopViewModel.fetchSeller()
        homeViewModel.fetchRecommendationProducts()
        shopViewModel.fetchInnovationProducts()


    }
   private fun setCatalogAdapter(){
        catalogAdapter = CatalogAdapter()
        binding.rcyCatalog.adapter = catalogAdapter
        binding.rcyCatalog.layoutManager = GridLayoutManager(context,3)
        shopViewModel.popularItems.observe(viewLifecycleOwner){
            catalogAdapter.submitList(it)
        }
    }

    private fun setDiscountProduct(){
        discountAdapter = DiscountAdapter(

            addProduct = {item , notify->
                homeViewModel.addProductToLikes(item, notify)

            }
            , deleteProduct = {item , notify->
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
        binding.rcyDiscount.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        shopViewModel.products.observe(viewLifecycleOwner){
            discountAdapter.submitList(it)
        }

    }

    private fun setBrandsAdapter(){
        brandsAdapter = BrandsAdapter()
        binding.rcyBrands.adapter = brandsAdapter
        binding.rcyBrands.layoutManager = LinearLayoutManager(requireContext() ,LinearLayoutManager.HORIZONTAL ,false)
        shopViewModel.brands.observe(viewLifecycleOwner){
            brandsAdapter.submitList(it)
        }
    }

    private  fun setSellerAdapter(){
        sellerAdapter = SellerAdapter()
        binding.rcySeller.adapter = sellerAdapter
        binding.rcySeller.layoutManager = LinearLayoutManager(requireContext() ,LinearLayoutManager.HORIZONTAL ,false)
        shopViewModel.seller.observe(viewLifecycleOwner){
            sellerAdapter.submitList(it)
        }
    }

    private fun setRecommendationProduct(){
        recommendationsAdapter = RecommendationAdapter(

            addProduct = {item , notify->
                homeViewModel.addProductToLikes(item, notify)

            }
            , deleteProduct = {item , notify->
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
                findNavController().navigate(R.id.action_mainFragment_to_moreProductFragment)
            }
        )
        binding.rcyRecomendations.adapter = recommendationsAdapter
        binding.rcyRecomendations.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        homeViewModel.recommendationProducts.observe(viewLifecycleOwner){
            recommendationsAdapter.submitList(it)
        }

    }


    private fun setInnovationProduct(){
        innovationAdapter = InnovationAdapter(

            addProduct = {item , notify->
                homeViewModel.addProductToLikes(item, notify)

            }
            , deleteProduct = {item , notify->
                homeViewModel.removeLikedProduct(item , notify)
            },
             homeViewModel = homeViewModel ,
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item , notify->
                homeViewModel.deleteProductToBasket(item,notify)
            }
        )
        binding.rcyInnovations.adapter = innovationAdapter
        binding.rcyInnovations.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        shopViewModel.innovationProduct.observe(viewLifecycleOwner){
            innovationAdapter.submitList(it)
        }

    }

    private fun click(){
        binding.imgBasket.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_basketProductFragment)
        }
        binding.layoutLike.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_likedProductFragment)
        }
        binding.more.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_moreProductFragment)
        }
        binding.moreSecond.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_moreProductFragment)
        }
        binding.moreThird.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_moreProductFragment)
        }
    }



}