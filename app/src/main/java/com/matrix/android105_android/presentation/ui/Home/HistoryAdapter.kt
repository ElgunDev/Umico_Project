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
import com.matrix.android105_android.databinding.ItemImageButtonBinding
import com.matrix.android105_android.databinding.ItemProductsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryAdapter(
    private val isProductLiked: suspend (String)->Boolean,
    private val addProduct:(product:Product , notify:()->Unit)->Unit,
    private val deleteProduct:(product:Product , notify:()->Unit)->Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeProduct = 1
    private val viewTypeImageButton = 2
    private val additionalImageButtonCount =1

    private var dataLoading = false

    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this ,diffCallBack)

    override fun getItemViewType(position: Int): Int {
        return if(position<diffUtil.currentList.size){
            viewTypeProduct
        }
        else if(dataLoading){
            viewTypeImageButton
        }
        else{
            viewTypeProduct
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       if (viewType == viewTypeProduct){
           val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
           return HistoryViewHolder(binding)
       }
        else{
            val binding = ItemImageButtonBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
           return ImageButtonViewHolder(binding)
       }
    }

    override fun getItemCount(): Int {
        if (dataLoading==true) {
            return diffUtil.currentList.size + additionalImageButtonCount
        }
        else{
            return  diffUtil.currentList.size
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypeProduct){
            val product = diffUtil.currentList[position]
            (holder as HistoryViewHolder).bind(product)
        }
        else{
            (holder as ImageButtonViewHolder).bind()
        }
    }

    inner class HistoryViewHolder(private val binding:ItemProductsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product){
            binding.txtCreditMonth.text = product.credit
            binding.discountedPrice.text = product.discountPrice
            binding.discountRate.text = product.discountPrice
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

    inner  class ImageButtonViewHolder(private val binding: ItemImageButtonBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(){

       }
    }

    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
        dataLoading =true
        notifyDataSetChanged()
    }
}