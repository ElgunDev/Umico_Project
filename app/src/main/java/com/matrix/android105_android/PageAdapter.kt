package com.matrix.android105_android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.matrix.android105_android.Home.HomeFragment
import com.matrix.android105_android.Profil.ProfilFragment
import com.matrix.android105_android.Services.ServicesFragment
import com.matrix.android105_android.Shop.ShopFragment
import com.matrix.android105_android.bonus.BonusFragment


class PageAdapter(fragmentManager: FragmentManager , lifecycle: Lifecycle ):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0->HomeFragment()
           1->ShopFragment()
           2->BonusFragment()
           3->ServicesFragment()
           4->ProfilFragment()
           else -> HomeFragment()
       }
    }
}