package com.matrix.android105_android.presentation.ui.Home

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.R
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.Shop
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.databinding.ItemImageButtonBinding
import com.matrix.android105_android.databinding.ItemProductsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ProductAdapter(
    private val isProductLiked: suspend (String) -> Boolean, // Higher-order function to check like status
    private val addProduct:(product: Product , notify:()->Unit)->Unit,
    private val deleteProduct:(product: Product,notify:()->Unit)->Unit,
    private val isProductBasket:suspend (String) -> Boolean,
    private val addProductBasket:(product: Product , notify:()->Unit)->Unit,
    private val deleteProductBasket:(product: Product,notify:()->Unit)->Unit,
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeProduct = 1
    private val viewTypeImageButton = 2
    private val additionalImageButtonCount = 1
    private var dataLoaded = false

    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun getItemViewType(position: Int): Int {
        return if (position<diffUtil.currentList.size){
              viewTypeProduct
        }
        else if (dataLoaded){
            viewTypeImageButton
        }
        else{
            viewTypeProduct
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == viewTypeProduct) {
            val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProductViewHolder(binding)
        }
        else{
            val binding = ItemImageButtonBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
            return ImageButtonViewHolder(binding)

        }
    }

    override fun getItemCount(): Int {
        return if (dataLoaded) {
            diffUtil.currentList.size + additionalImageButtonCount
        } else {
            diffUtil.currentList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypeProduct){
        val product = diffUtil.currentList[position]
            (holder as ProductViewHolder).bind(product)
         }
        else{
            (holder as ImageButtonViewHolder).bind()
        }

    }


    inner class ProductViewHolder(private val binding:ItemProductsBinding):RecyclerView.ViewHolder(binding.root){

         fun bind(product: Product){
             binding.txtCreditMonth.text = product.credit
             binding.discountedPrice.text = product.discountPrice
             binding.discountRate.text = product.discountRate
             binding.txtNameProduct.text = product.name
             binding.price.text = product.price
             binding.ratingBar.rating = product.rating.toFloat()
             Glide.with(binding.root.context)
                 .load(product.image)
                 .into(binding.imgProduct)

             if (product.id == "5"){
                 binding.discountRate.visibility = View.GONE
                 binding.price.visibility = View.GONE
             }



            if (product.countDownTimer == null){
                val totalTimer = (23*60*60*1000) + (59*60*1000) + (59*1000)
                product.countDownTimer = object :CountDownTimer(totalTimer.toLong() , 1000L){
                    override fun onTick(millisUntilFinished: Long) {
                        val hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                        val minute = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60
                        val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60
                        val timeFormat = String.format("%02d:%02d:%02d" , hour,minute,second)
                        binding.timer.text = timeFormat
                    }
                    override fun onFinish() {
                        binding.timer.text = "00:00:00"
                    }
                }
                product.countDownTimer!!.start()
            }

             binding.imgLike.setOnClickListener(){
                 CoroutineScope(Dispatchers.Main).launch {
                     val isLiked = isProductLiked(product.id)
                     if (!isLiked){
                         addProduct(product){
                             notifyDataSetChanged()
                         }
                         updateLikeButton(isLiked)
                     }
                     else{
                         deleteProduct(product){
                             notifyDataSetChanged()
                         }
                         updateLikeButton(!isLiked)
                     }
                 }
             }

             binding.btnAddBasket.setOnClickListener(){
                 CoroutineScope(Dispatchers.Main).launch {
                     val isBasket = isProductBasket(product.id)
                     if (!isBasket){
                         addProductBasket(product){
                             notifyDataSetChanged()
                         }
                         updateBasketButton(isBasket)
                     }
                     else{
                         deleteProductBasket(product){
                             notifyDataSetChanged()
                         }
                         updateBasketButton(!isBasket)

                     }
                 }
             }



             CoroutineScope(Dispatchers.Main).launch {
                 val isLiked = isProductLiked(product.id)
                 updateLikeButton(isLiked)
                 val isBasket = isProductBasket(product.id)
                 updateBasketButton(isBasket)
             }



         }
        private fun updateLikeButton(isLiked: Boolean) {
            if (isLiked) {
                binding.imgLike.setImageResource(R.drawable.favorite_24dp_fill1_wght400_grad0_opsz24) // Liked
            } else {
                binding.imgLike.setImageResource(R.drawable.favorite_fill0_wght400_grad0_opsz24) // Not liked
            }
        }

        private fun updateBasketButton(isBasket:Boolean){
            if (isBasket){
                binding.btnAddBasket.apply {
                    setTextColor(ContextCompat.getColor(context , R.color.green))
                    setText(R.string.in_the_basket)
                    setCompoundDrawablesWithIntrinsicBounds(
                        ContextCompat.getDrawable(context , R.drawable.shopping_cart_24dp_e8eaed_fill0_wght400_grad0_opsz24_green),
                        null,
                        null,
                        null
                    )
                }
            }
            else{
                binding.btnAddBasket.apply {
                    setTextColor(ContextCompat.getColor(context , R.color.BluePurple))
                    setText(R.string.Basket)
                    setCompoundDrawablesWithIntrinsicBounds(
                        ContextCompat.getDrawable(context , R.drawable.shopping_cart_24dp_e8eaed_fill0_wght400_grad0_opsz24_purple),
                        null,
                        null,
                        null
                    )
                }
            }
        }

         }
    inner class ImageButtonViewHolder(private val binding: ItemImageButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
        dataLoaded =true
        notifyDataSetChanged()

       }
    }
