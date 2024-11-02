package com.matrix.android105_android.presentation.ui.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matrix.android105_android.R
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.EndlessRecyclerViewScrollListener
import com.matrix.android105_android.databinding.FragmentHomeBinding
import com.matrix.android105_android.presentation.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adAdapter: AdAdapter
    private lateinit var shopAdapter:ShopsAdapter
    private lateinit var productAdapter:ProductAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter
    private lateinit var dowryAdapter:DowryAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var adSecondAdapter: AdSecondAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var adThirdAdapter: AdThirdAdapter
    private lateinit var actionAdapter: ActionAdapter
    private lateinit var allProductAdapter: AllProductAdapter
    private lateinit var endlessScrollListener: MyEndlessScrollListener

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
        homeViewModel.fetchDowryImages()
        homeViewModel.fetchHistoryProducts()
        homeViewModel.fetchAdSecondImages()
        homeViewModel.fetchPopular()
        homeViewModel.fetchAdThirdImages()
        homeViewModel.fetchActionImages()
        homeViewModel.loadProduct(false)
        observeUserName()
        setAdAdapter()
        setShopAdapter()
        observeTimer()
        setProductAdapter()
        setRecommendationAdapter()
//        setDowryAdapter()
        setHistoryAdapter()
        setAdSecondAdapter()
        setPopularAdapter()
        setAdThirdAdapter()
        setActionAdapter()
        setAllProductAdapter()
        clickLikedButton()
        clickBasketButton()
        clickProfilButton()

    }
    class MyEndlessScrollListener(
        layoutManager: LinearLayoutManager,
        private val loadMoreCallback: () -> Unit
    ) : EndlessRecyclerViewScrollListener(layoutManager) {

        override fun onLoadMore() {

            loadMoreCallback()
        }
    }

    private fun setAllProductAdapter(){
        allProductAdapter = AllProductAdapter(
            isProductLiked = {productId->
                homeViewModel.isProductLiked(productId)
            },
            addProduct = {item , notify->
                homeViewModel.addProductToLikes(item , notify)
            },
            deleteProduct = {item , notify->
                homeViewModel.removeLikedProduct(item , notify)
            },
            isProductBasket = {productId->
                homeViewModel.isProductBasket(productId)
            },
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item,notfy->
                homeViewModel.deleteProductToBasket(item,notfy)
            }
        )
        val layoutManager = GridLayoutManager(context,2)
        binding.rcyAllProduct.adapter =allProductAdapter
        binding.rcyAllProduct.layoutManager = layoutManager
        homeViewModel.productsLiveData.observe(viewLifecycleOwner){
            allProductAdapter.submitList(it)
        }
        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        endlessScrollListener = MyEndlessScrollListener(layoutManager) {
            homeViewModel.loadMoreProducts{println("SDHFGHFSDFGHFDSHJGHGSASD")}
        }

        binding.rcyAllProduct.addOnScrollListener(object : EndlessRecyclerViewScrollListener(binding.rcyAllProduct.layoutManager as GridLayoutManager){
            override fun onLoadMore() {
                homeViewModel.loadMoreProducts { println("SDHFGHFSDFGHFDSHJGHGSASD") }
            }
        })
//        binding.scrollView.(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                // Get the layout manager
//                val layoutManager = recyclerView.layoutManager as GridLayoutManager?
//
//                // Total number of items in the adapter
//                val totalItemCount = layoutManager!!.getItemCount()
//
//                // Last visible item position
//                val lastVisibleItemPosition = layoutManager!!.findLastVisibleItemPosition()
//
//                Log.d("RecyclerView", "Reached the end of the list!")
//
//                // Detect if user has scrolled to the end of the list
//                if (lastVisibleItemPosition == totalItemCount - 1) {
//                    // You have reached the end of the list
//                    Log.d("RecyclerView", "Reached the end of the list!")
//
//                    // Perform your action here (e.g., load more items)
//                }
//            }
//        })


    }

    private fun clickLikedButton(){
        binding.btnLike.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_likedProductFragment)
        }
    }
    private fun clickBasketButton(){
        binding.imgBasket.setOnClickListener(){
            findNavController().navigate(R.id.action_mainFragment_to_basketProductFragment)
        }
    }
    private fun clickProfilButton(){
        binding.btnProfilText.setOnClickListener() {
            findNavController().navigate(R.id.action_mainFragment_to_profilFragment)
        }
    }

    private fun setAdAdapter(){
        adAdapter = AdAdapter()
        binding.rcyAdvertising.adapter = adAdapter
        binding.rcyAdvertising.layoutManager  = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        homeViewModel.adImages.observe(viewLifecycleOwner){adImages->
            adAdapter.submitList(adImages)
        }
    }
    private fun setActionAdapter(){
        actionAdapter = ActionAdapter()
        binding.rcyActions.adapter = actionAdapter
        binding.rcyActions.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL ,false)
        homeViewModel.actionsImage.observe(viewLifecycleOwner){
            actionAdapter.submitList(it)
        }
    }

    private fun setAdSecondAdapter(){
        adSecondAdapter = AdSecondAdapter()
        binding.rcyAdvetisingSecond.adapter = adSecondAdapter
        val gridLayoutManager = GridLayoutManager(context , 6)
        gridLayoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(position){
                    3,4 ->3
                    else->2
                }
            }
            override fun getSpanGroupIndex(adapterPosition: Int, spanCount: Int): Int {
                return super.getSpanGroupIndex(adapterPosition, spanCount)
            }

        }
        binding.rcyAdvetisingSecond.layoutManager = gridLayoutManager
        homeViewModel.adSecondImages.observe(viewLifecycleOwner){
            adSecondAdapter.submitList(it)
        }


    }
    private fun setAdThirdAdapter(){
        adThirdAdapter = AdThirdAdapter()
        binding.rcyAdvertisingThird.adapter = adThirdAdapter
        val gridLayoutManager = GridLayoutManager(context , 6)
        gridLayoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(position){
                    3,4 ->3
                    else->2
                }
            }
            override fun getSpanGroupIndex(adapterPosition: Int, spanCount: Int): Int {
                return super.getSpanGroupIndex(adapterPosition, spanCount)
            }

        }
        binding.rcyAdvertisingThird.layoutManager = gridLayoutManager
        homeViewModel.adThirdImages.observe(viewLifecycleOwner){
            adThirdAdapter.submitList(it)
        }


    }
//    private fun setDowryAdapter(){
//       dowryAdapter = DowryAdapter()
//        binding.rcyDowry.adapter = dowryAdapter
//            val gridLayoutManager = GridLayoutManager(context, 6)
//            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
//                    return when (position) {
//                        3, 4 ,8,9 -> 3
//                        else -> 2
//                    }
//                }
//                override fun getSpanGroupIndex(adapterPosition: Int, spanCount: Int): Int {
//                    return super.getSpanGroupIndex(adapterPosition, spanCount)
//                }
//
//            }
//
//         binding.rcyDowry.layoutManager = gridLayoutManager
//        homeViewModel.dowryImage.observe(viewLifecycleOwner){dowryImages->
//            if (dowryImages != null) {
//                dowryAdapter.submitList(dowryImages)
//            }
//        }
//
//    }

    private fun setPopularAdapter(){
        popularAdapter = PopularAdapter()
        binding.rcyPopular.adapter = popularAdapter
        binding.rcyPopular.layoutManager = GridLayoutManager(context,3)
        homeViewModel.popularItems.observe(viewLifecycleOwner){
            popularAdapter.submitList(it)
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
      productAdapter = ProductAdapter(
          isProductLiked = {productId->
              homeViewModel.isProductLiked(productId)
          },
          addProduct = {item , notify->
              homeViewModel.addProductToLikes(item, notify)

          }
          , deleteProduct = {item , notify->
              homeViewModel.removeLikedProduct(item , notify)
          },
          isProductBasket = {productId->
              homeViewModel.isProductBasket(productId)
          },
          addProductBasket = {item,notify->
              homeViewModel.addProductToBasket(item,notify)
          },
          deleteProductBasket = {item , notify->
              homeViewModel.deleteProductToBasket(item,notify)
          }

      )
        binding.rcyDiscountedproducts.adapter = productAdapter
        binding.rcyDiscountedproducts.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL,false)
        homeViewModel.product.observe(viewLifecycleOwner){productList->
            productAdapter.submitList(productList)
        }
    }

    private fun setRecommendationAdapter(){
        recommendationAdapter = RecommendationAdapter(
            isProductLiked = {productId->
                homeViewModel.isProductLiked(productId)
            },
            addProduct = {item,notify->
                homeViewModel.addProductToLikes(item ,notify)

            },
            deleteProduct = { item, notify ->
                homeViewModel.removeLikedProduct(item , notify)
            },
            isProductBasket = {productId->
                homeViewModel.isProductBasket(productId)
            },
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item,notify->
                homeViewModel.deleteProductToBasket(item,notify)
            }
        )
        binding.rcyRecomendations.adapter = recommendationAdapter
        binding.rcyRecomendations.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL,false)
       homeViewModel.recommendationProducts.observe(viewLifecycleOwner){recommendationList->
           recommendationAdapter.submitList(recommendationList)
       }
    }
    private fun setHistoryAdapter(){
        historyAdapter = HistoryAdapter(
            isProductLiked = {productId->
                homeViewModel.isProductLiked(productId)
            },
            addProduct = {item , notify ->
                homeViewModel.addProductToLikes(item , notify)
            },
            deleteProduct = {item, notify->
                homeViewModel.removeLikedProduct(item , notify)
            },
            isProductBasket = {productId->
                homeViewModel.isProductBasket(productId)
            },
            addProductBasket = {item,notify->
                homeViewModel.addProductToBasket(item,notify)
            },
            deleteProductBasket = {item , notify->
                homeViewModel.deleteProductToBasket(item,notify)
            }
        )
        binding.rcyHistory.adapter = historyAdapter
        binding.rcyHistory.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        homeViewModel.historyProduct.observe(viewLifecycleOwner){historyList->
            historyAdapter.submitList(historyList)

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