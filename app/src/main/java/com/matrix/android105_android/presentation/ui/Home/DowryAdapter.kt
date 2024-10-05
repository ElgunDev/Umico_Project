package com.matrix.android105_android.presentation.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.R
import com.matrix.android105_android.data.Repository.Home.dowry.Dowry
import com.matrix.android105_android.databinding.ItemDowryBinding

class DowryAdapter:RecyclerView.Adapter<DowryAdapter.DowryViewHolder>() {



    private val diffCallBack = object :DiffUtil.ItemCallback<Dowry>(){
        override fun areItemsTheSame(oldItem: Dowry, newItem: Dowry): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Dowry, newItem: Dowry): Boolean {
            return  oldItem==newItem
        }


    }
    private val diffUtil = AsyncListDiffer(this,diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DowryViewHolder {
        val binding = ItemDowryBinding.inflate(LayoutInflater.from(parent.context) , parent ,false)
        return DowryViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: DowryViewHolder, position: Int) {
        val image  = diffUtil.currentList[position]
        return holder.bind(image.image)
    }

    inner class DowryViewHolder(private val binding:ItemDowryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(imageUrl:String){
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.itemImage)
        }

    }
    fun submitList(list: List<Dowry>){
        diffUtil.submitList(list)
    }
}