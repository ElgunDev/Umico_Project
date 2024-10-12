package com.matrix.android105_android.presentation.ui.LikedProduct

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> UmicoMarketFragment()
            1-> UmicoBonusFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }


}