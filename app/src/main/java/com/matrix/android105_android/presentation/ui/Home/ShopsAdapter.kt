package com.matrix.android105_android.presentation.ui.Home

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.Shops.Shop
import com.matrix.android105_android.databinding.ItemShopsBinding
import java.util.concurrent.TimeUnit

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
        return holder.bind(shop.imageUrl, shop.nameTop, shop.nameBottom)
    }

    inner class ShopsViewHolder(private val binding: ItemShopsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String, nameTop: String, nameBottom: String) {
            binding.txtShopsTop.text = nameTop
            binding.txtShopsBottom.text = nameBottom
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.imgShops)
        }
    }
    fun submitList(list: List<Shop>) {
        diffUtil.submitList(list)
    }
}
