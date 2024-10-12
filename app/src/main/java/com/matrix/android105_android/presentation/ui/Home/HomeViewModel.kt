package com.matrix.android105_android.presentation.ui.Home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.matrix.android105_android.data.Local.db.entity.BasketProductEntity
import com.matrix.android105_android.data.Local.db.entity.LikedProductEntity
import com.matrix.android105_android.data.Local.db.repository.BasketProductImplRepository
import com.matrix.android105_android.data.Local.db.repository.LikedProductImplRepository
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.Shop
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.advertisement.Advertisement
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.dowry.Dowry
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.popular.Popular
import com.matrix.android105_android.domain.Local.Repository.BasketProduct.IBasketProductRepository
import com.matrix.android105_android.domain.Local.Repository.LikedProduct.ILikedProductRepository
import com.matrix.android105_android.domain.UseCase.Home.Shops.ShopsUseCase
import com.matrix.android105_android.domain.UseCase.Profil.GetUserNameUseCase
import com.matrix.android105_android.domain.UseCase.Home.advertisement.AdUseCase
import com.matrix.android105_android.domain.UseCase.Home.dowry.DowryUseCase
import com.matrix.android105_android.domain.UseCase.Home.popular.PopularUseCase
import com.matrix.android105_android.domain.UseCase.Home.products.GetAllProductUseCase
import com.matrix.android105_android.domain.UseCase.Home.products.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserNameUseCase:GetUserNameUseCase,
    private val firebaseAuth: FirebaseAuth,
    private val adUseCase: AdUseCase,
    private val shopUseCase:ShopsUseCase,
    private val productUseCase: GetProductUseCase,
    private val dowryUseCase: DowryUseCase,
    private val popularUseCase: PopularUseCase,
    private val allProductUseCase: GetAllProductUseCase,
    private val likedProductImplRepository: ILikedProductRepository,
    private val basketProductImplRepository: IBasketProductRepository
):ViewModel() {
    private val _userName = MutableLiveData<String>()
    val username:MutableLiveData<String>
        get() = _userName

    private val _adImages = MutableLiveData<List<Advertisement>>()
    val adImages:MutableLiveData<List<Advertisement>>
        get() = _adImages

    private val _shops= MutableLiveData<List<Shop>>()
    val shops:MutableLiveData<List<Shop>>
        get() = _shops

    private val _timeRemaining = MutableLiveData<String>()
    val timeRemaining:MutableLiveData<String>
        get() = _timeRemaining

    private val _products = MutableLiveData<List<Product>>()
    val product:MutableLiveData<List<Product>>
        get()= _products

    private val _recommendationProducts = MutableLiveData<List<Product>>()
    val recommendationProducts:MutableLiveData<List<Product>>
        get()= _recommendationProducts

    private val _dowryImage = MutableLiveData<List<Dowry>>()
    val dowryImage:MutableLiveData<List<Dowry>>
        get() = _dowryImage

    private val _historyProducts = MutableLiveData<List<Product>>()
    val historyProduct:MutableLiveData<List<Product>>
        get() = _historyProducts

    private val _adSecondImages = MutableLiveData<List<Advertisement>>()
    val adSecondImages:MutableLiveData<List<Advertisement>>
        get()= _adSecondImages

    private val _adThirdImages = MutableLiveData<List<Advertisement>>()
    val adThirdImages: MutableLiveData<List<Advertisement>>
        get() = _adThirdImages

    private val _popularItems  = MutableLiveData<List<Popular>>()
    val popularItems :MutableLiveData<List<Popular>>
        get() = _popularItems

    private val _actionsImage = MutableLiveData<List<Advertisement>>()
    val actionsImage :MutableLiveData<List<Advertisement>>
        get() = _actionsImage


    private val _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> get() = _productsLiveData
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val allProducts = mutableListOf<Product>()

    val likedProduct: LiveData<List<LikedProductEntity>> = likedProductImplRepository.getLikedProduct()

    val basketProduct:LiveData<List<BasketProductEntity>> = basketProductImplRepository.getBasketProduct()

    private lateinit var countDownTimer: CountDownTimer
    private val totalTimer = (23*60*60*1000) + (59*60*1000) + (59*1000)


    fun addProductToLikes(product: Product,notify: () -> Unit){
        viewModelScope.launch {
            likedProductImplRepository.addProductToDatabase(
                LikedProductEntity(
                    product.id,
                    product.credit,
                    product.category,
                    product.discountPrice,
                    product.discountRate,
                    product.image,
                    product.name,
                    product.price,
                    product.rating
                )
            )
            val isLiked =  isProductLiked(product.id)
           if (isLiked){
               notify()
           }
        }
    }

    fun removeLikedProduct(product: Product ,notify: () -> Unit){
        viewModelScope.launch {
            likedProductImplRepository.deleteLikedProduct(product.id)
            val isLiked = isProductLiked(product.id)
            if (!isLiked){
                notify
            }
        }
    }

    suspend fun isProductLiked(productId:String):Boolean{
            return likedProductImplRepository.isProductLiked(productId)
    }

    fun addProductToBasket(product: Product , notify: () -> Unit){
        viewModelScope.launch {
            basketProductImplRepository.addProductToDatabase(
                BasketProductEntity(
                    product.id,
                    product.credit,
                    product.category,
                    product.discountPrice,
                    product.discountRate,
                    product.image,
                    product.name,
                    product.price,
                    product.rating
                )
            )
            val isBasket = isProductBasket(product.id)
            if (isBasket){
                notify()
            }
        }
    }
    fun deleteProductToBasket(product: Product , notify: () -> Unit){
        viewModelScope.launch {
            basketProductImplRepository.deleteBasketProduct(product.id)
            val isBasket = isProductBasket(product.id)
            if (!isBasket){
                notify
            }
        }
    }

    suspend fun isProductBasket(productId: String):Boolean{
        return basketProductImplRepository.isProductBasket(productId)
    }


     fun loadProduct(loadMore:Boolean) {
         if (_isLoading.value == true) return
         if (!loadMore) {
             allProducts.clear()
         }
         _isLoading.value = true

         viewModelScope.launch {
            allProductUseCase.getAllProducts(loadMore) { result ->
                println("BBSVDKDGDHGKHLHGKJGHSJHGSLKGHHLSHGHGJKDSGFHKS")
                result.onSuccess { products ->
                    println("SSNDJKGHDJKHGDDJHGKDNKHGDKGDSIHGKIGHIIDGHKJG")
                    allProducts.addAll(products)
                    _productsLiveData.value = allProducts
                }.onFailure { error ->
                    // Handle error (e.g., show a message to the user)
                    _isLoading.value = false
                }
            }
        }
    }

    // Load more products for infinite scroll
    fun loadMoreProducts(callback: () -> Unit) {
        if (_isLoading.value == true) return

        viewModelScope.launch {
            _isLoading.value = true // Show loading state

            allProductUseCase.getAllProducts(true) { result ->
                println("BSVDKDGDHGKHLHGKJGHSJHGSLKGHHLSHGHGJKDSGFHKS")
                result.onSuccess { newProducts ->
                    println("SNDJKGHDJKHGDDJHGKDNKHGDKGDSIHGKIGHIIDGHKJG")
                    if (newProducts.isNotEmpty()) {
                        allProducts.addAll(newProducts)
                        _productsLiveData.value = allProducts
                    } else {
                        println("No more products available to load.")
                    }
                }.onFailure { error ->
                    // Handle error (e.g., log it or show a message to the user)
                    println("Error loading more products: ${error.message}")
                }
                _isLoading.value = false // Hide loading state
                callback() // Notify that loading is finished
            }
        }
    }

    fun fetchUserName(){
        val uid = firebaseAuth.currentUser?.uid
                if(uid !=null){
                    viewModelScope.launch {
                        val result = getUserNameUseCase.invoke(uid)
                        result.onSuccess {name->
                            _userName.value = name
                        }
                            .onFailure {exception->
                                _userName.value = "Error : ${exception.message}"
                            }
                    }
                }
    }
    fun fetchAdImages(){
        viewModelScope.launch {
            try {
                val images = adUseCase.getAdvertisementImages()
                val filterImages = images.filter {
                    it.row == "first"
                }
                _adImages.value = filterImages
            }
            catch (e:Exception){
            }
        }
    }

    fun fetchAdSecondImages(){
        viewModelScope.launch {
            try {
                val images = adUseCase.getAdvertisementImages()
                val filterImages = images.filter {
                    it.row == "second"
                }
                _adSecondImages.value = filterImages
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchAdThirdImages(){
        viewModelScope.launch {
            try {
                val images = adUseCase.getAdvertisementImages()
                val filterImages = images.filter {
                    it.row == "third"
                }
                _adThirdImages.value = filterImages
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchActionImages(){
        viewModelScope.launch {
            try {
                val images = adUseCase.getAdvertisementImages()
                val filterImage = images.filter {
                    it.row == "fourth"
                }
                _actionsImage.value = filterImage
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchDowryImages(){
        viewModelScope.launch {
            try {
                val images = dowryUseCase.getDowry()
                _dowryImage.value = images
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchPopular(){
        viewModelScope.launch {
            try {
                val popularItems = popularUseCase.getPopular()
                _popularItems.value = popularItems
            }
            catch (e:Exception){

            }
        }
    }



    fun fetchShops(){
        viewModelScope.launch {
            try {
                val shopList = shopUseCase.getShops()
                _shops.value = shopList
            }
            catch (e:Exception){

            }
        }
    }
    fun fetchProducts(){
        viewModelScope.launch {
            try {
                val productList =productUseCase.getProducts()
                val filteredProduct = productList.filter {
                    it.category == "məişət" || it.category == "təmizlik"
                }
                _products.value = filteredProduct
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchRecommendationProducts(){
        viewModelScope.launch {
            try {
                val productList =productUseCase.getProducts()
                val filteredProduct = productList.filter {
                    it.category == "telefon" || it.category == "saat"
                }
                _recommendationProducts.value = filteredProduct
            }
            catch (e:Exception){

            }
        }
    }

    fun fetchHistoryProducts(){
        viewModelScope.launch {
            try {
                val productList = productUseCase.getProducts()
                val filteredProducts = productList.filter {
                    it.category == "məişət" || it.category == "telefon"
                }
                _historyProducts.value = filteredProducts
            }
            catch (e:Exception){

            }
        }
    }

    fun startCountDown(){
        countDownTimer = object :CountDownTimer(totalTimer.toLong() , 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                val minute = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60
                val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60
                val timeFormat = String.format("%02d:%02d:%02d" , hour,minute,second)
                _timeRemaining.value = timeFormat
            }

            override fun onFinish() {
                _timeRemaining.value = "00:00:00"
            }

        }
        countDownTimer.start()

    }

}