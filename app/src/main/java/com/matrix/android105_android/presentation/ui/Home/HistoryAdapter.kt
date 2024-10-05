package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.Products.Product
import com.matrix.android105_android.databinding.ItemImageButtonBinding
import com.matrix.android105_android.databinding.ItemProductsBinding

class HistoryAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            (holder as HistoryViewHolder).bind(product.image , product.credit,product.discountPrice, product.discountRate,product.name , product.price)
        }
        else{
            (holder as ImageButtonViewHolder).bind()
        }
    }

    inner class HistoryViewHolder(private val binding:ItemProductsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(  imageUrl: String,
                   credit: String,
                   discountPrize: String,
                   discountRate: String,
                   name: String,
                   price: String,){
            binding.txtCreditMonth.text = credit
            binding.discountedPrice.text = discountPrize
            binding.discountRate.text = discountRate
            binding.txtNameProduct.text = name
            binding.price.text = price
            binding.txtAction.text = ""
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.imgProduct)
        }

    }

    inner  class ImageButtonViewHolder(private val binding: ItemImageButtonBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(){}
    }

    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
        dataLoading =true
        notifyDataSetChanged()
    }
}