package com.matrix.android105_android.presentation.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.databinding.ItemAdvertisingBinding

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Advertisements>(){
        override fun areItemsTheSame(oldItem: Advertisements, newItem: Advertisements): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Advertisements, newItem: Advertisements): Boolean {
            return oldItem==newItem
        }

    }
    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemAdvertisingBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
             return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }

    inner class HomeViewHolder(private val binding:ItemAdvertisingBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(advertisements: Advertisements){
            Glide.with(binding.root.context)
                .load(advertisements.images)
                .into(binding.imgAdvertising)
        }

    }
    fun submitList(list: List<Advertisements>){
        diffUtil.submitList(list)
    }
}