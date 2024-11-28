package com.matrix.android105_android.presentation.ui.Shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.brends.Brands
import com.matrix.android105_android.data.Network.fireBase.Repository.shop.seller.Seller
import com.matrix.android105_android.databinding.ItemBrandsBinding

class SellerAdapter: RecyclerView.Adapter<SellerAdapter.SellerViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<Seller>(){
        override fun areItemsTheSame(oldItem: Seller, newItem: Seller): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Seller, newItem: Seller): Boolean {
            return oldItem==newItem
        }


    }

    private val diffUtil  = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        val binding = ItemBrandsBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return SellerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        val seller = diffUtil.currentList[position]
        return holder.bind(seller)
    }

    inner class SellerViewHolder(private val binding: ItemBrandsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(seller: Seller){
            Glide.with(binding.root.context)
                .load(seller.imageUrl)
                .into(binding.imgBrands)
        }
    }

    fun submitList(list: List<Seller>){
        diffUtil.submitList(list)
    }
}