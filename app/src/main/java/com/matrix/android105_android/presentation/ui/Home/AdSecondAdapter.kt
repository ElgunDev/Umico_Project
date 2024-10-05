package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.advertisement.Advertisement
import com.matrix.android105_android.databinding.ItemAdvertisingBinding
import com.matrix.android105_android.databinding.ItemDowryBinding

class AdSecondAdapter:RecyclerView.Adapter<AdSecondAdapter.AdSecondViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Advertisement>(){
        override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean {
           return oldItem==newItem
        }


    }
    private val diffUtil = AsyncListDiffer(this,diffCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdSecondViewHolder {
        val binding = ItemDowryBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return AdSecondViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AdSecondViewHolder, position: Int) {
            val advertisementSecond = diffUtil.currentList[position]
            return holder.bind(advertisementSecond.imageUrl)
        }

    inner class AdSecondViewHolder(private val binding: ItemDowryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.itemImage)
        }
    }

    fun submitList(list: List<Advertisement>){
        diffUtil.submitList(list)
    }
}