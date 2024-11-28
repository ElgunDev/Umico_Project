package com.matrix.android105_android.presentation.ui.bonus

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matrix.android105_android.databinding.FragmentBonusBinding
import com.matrix.android105_android.databinding.ItemTabBinding

class TabAdapter(
    private val list: List<String>,
    private val onTabSelected: (position: Int) -> Unit
):RecyclerView.Adapter<TabAdapter.TabAdapterViewHolder>() {
    private var selectedPosition = 0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabAdapterViewHolder {
        val binding = ItemTabBinding.inflate(LayoutInflater.from(parent.context) ,parent, false)
        return TabAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: TabAdapterViewHolder, position: Int) {
        holder.bind(list[position], position == selectedPosition )
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onTabSelected(position)
        }
    }

    inner class TabAdapterViewHolder(private val binding: ItemTabBinding):RecyclerView.ViewHolder(binding.root){
          fun bind(text:String ,isSelected:Boolean ){
              binding.tabText.text =text
              itemView.isSelected =isSelected
              binding.tabText.setTextColor(
                  if (isSelected){
                      Color.WHITE
                  }
                  else{
                      Color.BLACK
                  }
              )
          }
    }
}