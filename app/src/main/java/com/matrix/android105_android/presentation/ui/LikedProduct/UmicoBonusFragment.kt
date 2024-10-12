package com.matrix.android105_android.presentation.ui.LikedProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentUmicoBonusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UmicoBonusFragment : Fragment() {
    private lateinit var binding:FragmentUmicoBonusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUmicoBonusBinding.inflate(layoutInflater)
        return binding.root
    }
}