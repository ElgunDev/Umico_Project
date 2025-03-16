package com.matrix.android105_android.presentation.ui.bonus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.network.fireBase.Repository.bonus.partners.Partners
import com.matrix.android105_android.databinding.ItemPartnersBinding

class PartnersAdapter:RecyclerView.Adapter<PartnersAdapter.PartnersViewHolder>() {


    private val diffCallBack = object : DiffUtil.ItemCallback<Partners>(){
        override fun areItemsTheSame(oldItem: Partners, newItem: Partners): Boolean {
           return  oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Partners, newItem: Partners): Boolean {
         return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnersViewHolder {
        val binding = ItemPartnersBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return PartnersViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return  diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: PartnersViewHolder, position: Int) {
        val partners = diffUtil.currentList[position]
        return holder.bind(partners)
    }

    inner class PartnersViewHolder(private val binding:ItemPartnersBinding):RecyclerView.ViewHolder(binding.root){
      fun bind(partners: Partners){
          Glide.with(binding.root.context)
              .load(partners.images)
              .into(binding.partnersImages)
      }
    }

    fun submitList(list: List<Partners>){
        diffUtil.submitList(list)
    }
}