package com.matrix.android105_android.presentation.ui.likedProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.matrix.android105_android.databinding.FragmentLikedProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedProductFragment : Fragment() {
    private lateinit var binding:FragmentLikedProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        clickBack()
    }

    private fun setUpAdapter(){
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout , binding.viewPager){tab,position->
            when(position){
                0->tab.text = "UMICO MARKET"
                1->tab.text = "UMICO BONUS"
            }
        }.attach()
    }

    private fun clickBack(){
        binding.backButton.setOnClickListener(){
            findNavController().popBackStack()
        }
    }

}