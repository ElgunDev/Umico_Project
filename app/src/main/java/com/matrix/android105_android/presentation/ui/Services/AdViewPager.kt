package com.matrix.android105_android.presentation.ui.Services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.databinding.ItemAdImagesBinding

class AdViewPager(private val items:List<Advertisements>):RecyclerView.Adapter<AdViewPager.AdViewPagerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewPagerViewHolder {
        val binding = ItemAdImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AdViewPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class AdViewPagerViewHolder(private val binding:ItemAdImagesBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(images:Advertisements){
            binding.imgAdvertisements.setImageResource(images.images)
        }
    }
}