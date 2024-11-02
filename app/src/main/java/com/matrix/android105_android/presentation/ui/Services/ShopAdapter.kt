package com.matrix.android105_android.presentation.ui.Services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Shops.Shop
import com.matrix.android105_android.databinding.ItemShopsBinding

class ShopAdapter:RecyclerView.Adapter<ShopAdapter.ShopViewModel>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Shop>(){
        override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
            return oldItem ==newItem
        }

        override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
           return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewModel {
        val binding = ItemShopsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ShopViewModel(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ShopViewModel, position: Int) {
        val shops = diffUtil.currentList[position]
        return holder.bind(shops)
    }
    inner class  ShopViewModel(private val binding:ItemShopsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(shop:Shop){
            binding.txtShopsTop.text = shop.nameTop
            binding.txtShopsBottom.text = shop.nameBottom
            Glide.with(binding.root.context)
                .load(shop.imageUrl)
                .into(binding.imgShops)

        }
    }

    fun submitList(list:List<Shop>){
        diffUtil.submitList(list)

    }
}