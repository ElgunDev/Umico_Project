package com.matrix.android105_android.presentation.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    fun setAdapter(){
        val advertisements = listOf(
            Advertisements(R.drawable.image1),
            Advertisements(R.drawable.image2),
            Advertisements(R.drawable.image3),
            Advertisements(R.drawable.image4),
            Advertisements(R.drawable.image5),
            Advertisements(R.drawable.image6)
        )

        adapter = HomeAdapter()
        binding.rcyAdvertising.adapter = adapter
        binding.rcyAdvertising.layoutManager  = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        adapter.submitList(advertisements)
    }


}