package com.matrix.android105_android.presentation.ui.bonus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentBonusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusFragment : Fragment() {
    lateinit var binding:FragmentBonusBinding
    private lateinit var tabAdapter: TabAdapter
    private lateinit var partnersAdapter: PartnersAdapter

    private val bonusViewModel:BonusViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBonusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabAdapter()
        setPartnersAdapter()
        bonusViewModel.fetchPartnersImages()

    }

    private fun setTabAdapter(){
        val tabList = listOf("Hamısı", "Tərəfdaşlar", "Brendlər", "Aksiyalar")
        tabAdapter = TabAdapter(tabList){

        }
        binding.rcyViewTabs.adapter = tabAdapter
        binding.rcyViewTabs.layoutManager=LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
    }

    private fun setPartnersAdapter(){
        partnersAdapter = PartnersAdapter()
        binding.rcyPartners.adapter =partnersAdapter
        binding.rcyPartners.layoutManager = GridLayoutManager(context,2)
        bonusViewModel.partnersImages.observe(viewLifecycleOwner){
            partnersAdapter.submitList(it)
        }
    }


}