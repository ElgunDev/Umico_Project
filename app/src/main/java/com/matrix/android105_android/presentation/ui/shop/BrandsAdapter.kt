package com.matrix.android105_android.presentation.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.network.fireBase.Repository.shop.brends.Brands
import com.matrix.android105_android.databinding.ItemBrandsBinding

class BrandsAdapter:RecyclerView.Adapter<BrandsAdapter.BrandViewHolder>() {
    private val diffCallBack = object :DiffUtil.ItemCallback<Brands>(){
        override fun areItemsTheSame(oldItem: Brands, newItem: Brands): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Brands, newItem: Brands): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil  =AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = ItemBrandsBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BrandViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return  diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brands = diffUtil.currentList[position]
       return holder.bind(brands)
    }

    inner class BrandViewHolder(private val binding:ItemBrandsBinding):RecyclerView.ViewHolder(binding.root){
          fun bind(brands: Brands){
              Glide.with(binding.root.context)
                  .load(brands.image)
                  .into(binding.imgBrands)
          }
    }

    fun submitList(list: List<Brands>){
        diffUtil.submitList(list)
    }
}