package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.databinding.ItemAdvertisingBinding

class AdAdapter:RecyclerView.Adapter<AdAdapter.AdViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }


    }
    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding = ItemAdvertisingBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return AdViewHolder(binding)
    }

    override fun getItemCount(): Int {
             return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }

    inner class AdViewHolder(private val binding:ItemAdvertisingBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(imageUrl: String){
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.imgAdvertising)
        }

    }
    fun submitList(list: List<String>){
        diffUtil.submitList(list)
    }
}