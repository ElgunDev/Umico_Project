package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.advertisement.Advertisement
import com.matrix.android105_android.databinding.ItemDowryBinding

class AdThirdAdapter:RecyclerView.Adapter<AdThirdAdapter.AdThirdViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Advertisement>(){
        override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean {
        return    oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean {
        return  oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this , diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdThirdViewHolder {
       val binding = ItemDowryBinding.inflate(LayoutInflater.from(parent.context) , parent,false)
        return AdThirdViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AdThirdViewHolder, position: Int) {
        val item = diffUtil.currentList[position]
       return holder.bind(item.imageUrl)
    }

    inner class AdThirdViewHolder(private val binding:ItemDowryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind (imageUrl:String){
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.itemImage)
        }

    }

    fun submitList(list: List<Advertisement>){
        diffUtil.submitList(list)
    }

}