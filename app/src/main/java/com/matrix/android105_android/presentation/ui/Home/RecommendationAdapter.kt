package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.R
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.databinding.ItemImageButtonBinding
import com.matrix.android105_android.databinding.ItemProductsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class RecommendationAdapter(
    private val addProduct:(product: Product , notify:()->Unit)->Unit,
    private val deleteProduct:(product: Product,notify:()->Unit)->Unit,
    private val homeViewModel: HomeViewModel,
    private val addProductBasket:(product:Product , notify:()->Unit)->Unit,
    private val deleteProductBasket:(product:Product , notify:()->Unit)->Unit,
    private val onClick: ()->Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypeProduct = 1
    private val viewTypeImagButton =2
    private val additionalImageButtonCount =1

    private var dataLoading =false

    private val diffCallBack = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
          return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)

    override fun getItemViewType(position: Int): Int {
        return if (position<diffUtil.currentList.size){
            viewTypeProduct
        }
        else if(dataLoading){
            viewTypeImagButton
        }
        else{
            viewTypeProduct
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == viewTypeProduct) {
            val binding =
                ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RecommendationViewHolder(binding)
        }
        else{
            val binding = ItemImageButtonBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
            return ImageButtonViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        if (dataLoading) {
            return diffUtil.currentList.size +additionalImageButtonCount
        }
        else{
            return diffUtil.currentList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypeProduct){
        val product = diffUtil.currentList[position]
            (holder as RecommendationViewHolder).bind(product)
}
        else{
            (holder as ImageButtonViewHolder).bind()
        }
    }

    inner class RecommendationViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product:Product) {
            binding.txtCreditMonth.text = product.credit
            binding.discountedPrice.text = product.discountPrice.toString()
            binding.discountRate.text = product.discountRate
            binding.txtNameProduct.text = product.name
            binding.price.text = product.price.toString()
            binding.txtAction.visibility = View.GONE
            binding.ratingBar.rating = product.rating.toFloat()
            Glide.with(binding.root.context)
                .load(product.image)
                .into(binding.imgProduct)
            if (product.id == "5"){
                binding.discountRate.visibility = View.GONE
                binding.price.visibility = View.GONE
            }

            val isBasket = homeViewModel.basketStatus.value?.get(product.id) ?: false
            updateBasketButton(isBasket)

            val isLiked = homeViewModel.likedStatus.value?.get(product.id) ?: false
            updateLikeButton(isLiked)

            binding.imgLike.setOnClickListener(){
                CoroutineScope(Dispatchers.Main).launch {
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
                    if (!isBasket) {
                        addProductBasket(product) {
                            notifyDataSetChanged()
                        }
                        updateBasketButton(isBasket)
                    }
                    else{
                        deleteProductBasket(product){
                            notifyDataSetChanged()
                        }
                    }
                    updateBasketButton(!isBasket)
                }
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
    inner class ImageButtonViewHolder(private val binding:ItemImageButtonBinding):RecyclerView.ViewHolder(binding.root){
        fun  bind(){
         binding.imgButton.setOnClickListener(){
             onClick.invoke()
         }
        }
    }
    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
        dataLoading =true
        notifyDataSetChanged()
    }
}