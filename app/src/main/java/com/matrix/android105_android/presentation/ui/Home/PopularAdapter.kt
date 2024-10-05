package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.popular.Popular
import com.matrix.android105_android.databinding.ItemPopularBinding

class PopularAdapter:RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Popular>(){
        override fun areItemsTheSame(oldItem: Popular, newItem: Popular): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Popular, newItem: Popular): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popularItems = diffUtil.currentList[position]
        return holder.bind(popularItems.imageUrl , popularItems.text)
    }

    inner class PopularViewHolder(private val binding:ItemPopularBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(imageUrl:String,text:String){
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.imgPopular)
            binding.txtPopularItem.text = text

        }

    }

    fun submitList(list: List<Popular>){
        diffUtil.submitList(list)
    }
}