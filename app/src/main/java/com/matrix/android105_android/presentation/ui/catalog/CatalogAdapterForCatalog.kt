package com.matrix.android105_android.presentation.ui.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.network.fireBase.Repository.home.popular.Popular
import com.matrix.android105_android.databinding.ItemPopularBinding

class CatalogAdapterForCatalog: RecyclerView.Adapter<CatalogAdapterForCatalog.CatalogViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Popular>(){
        override fun areItemsTheSame(oldItem: Popular, newItem: Popular): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Popular, newItem: Popular): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return CatalogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val popularItems = diffUtil.currentList[position]
        return holder.bind(popularItems)
    }

    inner class CatalogViewHolder(private val binding: ItemPopularBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(popular: Popular){
            Glide.with(binding.root.context)
                .load(popular.imageUrl)
                .into(binding.imgPopular)
            binding.txtPopularItem.text = popular.text

        }

    }

    fun submitList(list: List<Popular>){
        diffUtil.submitList(list)
    }
}