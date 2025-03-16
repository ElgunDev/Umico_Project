package com.matrix.android105_android.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.network.fireBase.Repository.home.shops.Shop
import com.matrix.android105_android.databinding.ItemShopsBinding

class ShopsAdapter:RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder>( ) {

    private val diffCallBack = object : DiffUtil.ItemCallback<Shop>() {
        override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
            return newItem == oldItem
        }


    }
    private val diffUtil = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsViewHolder {
        val binding = ItemShopsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {
        val shop = diffUtil.currentList[position]
        return holder.bind(shop)
    }

    inner class ShopsViewHolder(private val binding: ItemShopsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop:Shop) {
            binding.txtShopsTop.text = shop.nameTop
            binding.txtShopsBottom.text = shop.nameBottom
            Glide.with(binding.root.context)
                .load(shop.imageUrl)
                .into(binding.imgShops)
        }
    }
    fun submitList(list: List<Shop>) {
        diffUtil.submitList(list)
    }
}
