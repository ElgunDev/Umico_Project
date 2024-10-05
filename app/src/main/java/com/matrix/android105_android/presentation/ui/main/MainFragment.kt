package com.matrix.android105_android.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupBottomNavMenu()
    }

    private fun setupViewPager() {

        val adapter = PageAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
    }

    private fun setupBottomNavMenu() {
        val viewPager = binding.viewPager
        binding.bottomNavMenu.setOnNavigationItemSelectedListener {
            binding.viewPager.currentItem = when (it.itemId) {
                R.id.home -> 0
                R.id.shop -> 1
                R.id.bonus -> 2
                R.id.services -> 3
                R.id.profil -> 4
                else -> 0
            }
            true
        }
        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavMenu.selectedItemId = when(position){
                    0 -> R.id.home
                    1 -> R.id.shop
                    2 -> R.id.bonus
                    3 -> R.id.services
                    4 -> R.id.profil
                    else -> R.id.home
                }
            }

        }
        )


    }
}