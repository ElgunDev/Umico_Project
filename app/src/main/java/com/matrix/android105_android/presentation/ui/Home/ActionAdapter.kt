package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.advertisement.Advertisement
import com.matrix.android105_android.databinding.ItemActionsBinding
import com.matrix.android105_android.databinding.ItemAdvertisingBinding

class ActionAdapter:RecyclerView.Adapter<ActionAdapter.ActionVIewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Advertisement>(){
        override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean {
            return newItem==oldItem
        }

        override fun areContentsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean {
           return newItem==oldItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionVIewHolder {
        val binding = ItemActionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActionVIewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ActionVIewHolder, position: Int) {
        val image  = diffUtil.currentList[position]
        return holder.bind(image.imageUrl)
    }

    inner class ActionVIewHolder(private val binding: ItemActionsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(imageUrl:String){
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.imgActions)
        }
    }
    fun submitList(list: List<Advertisement>){
        diffUtil.submitList(list)
    }
}