package com.matrix.android105_android.presentation.ui.Catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.matrix.android105_android.R
import com.matrix.android105_android.databinding.FragmentCatalogBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatalogFragment : Fragment() {
   private lateinit var  binding: FragmentCatalogBinding
   private lateinit var catalogAdapterForCatalog: CatalogAdapterForCatalog
   private val catalogViewModel: CatalogViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentCatalogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catalogViewModel.fetchPopularItems()
        setPopularAdapter()
        click()
    }

    private fun setPopularAdapter(){
        catalogAdapterForCatalog = CatalogAdapterForCatalog()
        binding.rcyPopular.adapter = catalogAdapterForCatalog
        binding.rcyPopular.layoutManager = GridLayoutManager(context,3)
        catalogViewModel.popularItems.observe(viewLifecycleOwner){
            catalogAdapterForCatalog.submitList(it)
        }
    }
     private  fun click(){
         binding.backButton.setOnClickListener(){
             findNavController().popBackStack()
         }
         binding.btnLike.setOnClickListener(){
             findNavController().navigate(R.id.action_catalogFragment_to_likedProductFragment)
         }
         binding.imgBasket.setOnClickListener(){
             findNavController().navigate(R.id.action_catalogFragment_to_basketProductFragment)
         }
     }

}