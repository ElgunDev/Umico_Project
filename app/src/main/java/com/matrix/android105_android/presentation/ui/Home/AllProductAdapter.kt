package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.R
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.databinding.ItemProductsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllProductAdapter(
    private val isProductLiked:suspend (String)->Boolean,
    private val addProduct:(product:Product , notify:()->Unit)->Unit,
    private val deleteProduct:(product:Product , notify:()->Unit)->Unit
):RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this , diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder {
       val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return AllProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) {
        val product = diffUtil.currentList[position]
        return holder.bind(product)
    }

    inner class AllProductViewHolder(private val binding:ItemProductsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) {
            binding.txtCreditMonth.text = product.credit
            binding.discountedPrice.text = product.discountPrice
            binding.discountRate.text = product.discountRate
            binding.txtNameProduct.text = product.name
            binding.price.text = product.price
            binding.txtAction.visibility = View.GONE
            Glide.with(binding.root.context)
                .load(product.image)
                .into(binding.imgProduct)
            if (product.id == "5"){
                binding.discountRate.visibility = View.GONE
                binding.price.visibility = View.GONE
            }

            binding.imgLike.setOnClickListener(){
                CoroutineScope(Dispatchers.Main).launch {
                    val isLiked = isProductLiked(product.id)
                    if (!isLiked) {
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

            CoroutineScope(Dispatchers.Main).launch {
                val isLiked = isProductLiked(product.id)
                updateLikeButton(isLiked)
            }
        }

        private fun updateLikeButton(isLiked:Boolean){
            if (isLiked){
              binding.imgLike.setImageResource(R.drawable.favorite_24dp_fill1_wght400_grad0_opsz24)
            }
            else{
                binding.imgLike.setImageResource(R.drawable.favorite_fill0_wght400_grad0_opsz24)
            }
        }

    }
    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
    }

}