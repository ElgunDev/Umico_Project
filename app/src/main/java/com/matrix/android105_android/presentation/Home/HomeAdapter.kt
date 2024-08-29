package com.matrix.android105_android.presentation.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.matrix.android105_android.databinding.ItemAdvertisingBinding

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<>(){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemAdvertisingBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class HomeViewHolder(private val binding:ItemAdvertisingBinding):RecyclerView.ViewHolder(binding.root){

    }
}