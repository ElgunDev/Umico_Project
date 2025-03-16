package com.matrix.android105_android.presentation.ui.likedProduct

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.databinding.ItemProductsBinding

class LikedProductAdapter():RecyclerView.Adapter<LikedProductAdapter.LikedProductViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }


    }
    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedProductViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return LikedProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
     return   diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: LikedProductViewHolder, position: Int) {
        val likedProduct = diffUtil.currentList[position]
        return holder.bind(likedProduct)
    }

    inner class LikedProductViewHolder(private val binding: ItemProductsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(likedProduct:Product) {
            binding.txtCreditMonth.text = likedProduct.credit
            binding.discountedPrice.text = likedProduct.discountPrice.toString()
            binding.discountRate.text = likedProduct.discountRate
            binding.txtNameProduct.text = likedProduct.name
            binding.price.text = likedProduct.price.toString()
            binding.txtAction.visibility = View.GONE
            Glide.with(binding.root.context)
                .load(likedProduct.image)
                .into(binding.imgProduct)

        }

    }

    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
    }

}