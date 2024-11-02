package com.matrix.android105_android.presentation.ui.LikedProduct

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Local.db.entity.LikedProductEntity
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.databinding.ItemProductsBinding

class LikedProductAdapter():RecyclerView.Adapter<LikedProductAdapter.LikedProductViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<LikedProductEntity>(){
        override fun areItemsTheSame(
            oldItem: LikedProductEntity,
            newItem: LikedProductEntity
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: LikedProductEntity,
            newItem: LikedProductEntity
        ): Boolean {
            return  oldItem==newItem
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
        fun bind(likedProduct:LikedProductEntity) {
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

    fun submitList(list: List<LikedProductEntity>){
        diffUtil.submitList(list)
    }

}