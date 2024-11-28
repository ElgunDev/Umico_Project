package com.matrix.android105_android.presentation.ui.ProfilDetailed

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.matrix.android105_android.R


import com.matrix.android105_android.databinding.FragmentProfilDetailedBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilDetailedFragment : Fragment() {
    private lateinit var binding:FragmentProfilDetailedBinding
    private val profilDetailedViewModel : ProfilDetailedViewModel by activityViewModels()

    private val cameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            openCamera()
        } else {

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfilDetailedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observes()
        click()
        profilDetailedViewModel.loadProfileImage()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun click(){
        binding.imgBackButton.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.fabContainer.setOnClickListener(){
            showOptionDialog()
        }
        binding.layoutPersounalInformation.setOnClickListener(){
            findNavController().navigate(R.id.action_profilDetailedFragment_to_personalInformationFragment)
        }
    }

    private fun showOptionDialog(){
        val option = arrayOf("Kamera" , "Qalareya")
        AlertDialog.Builder(requireContext())
            .setTitle("Seçim edin")
            .setItems(option){_,which->
                when(which){
                    0-> checkCameraPermission()
                    1-> openGallery()
                }

            }
            .show()
    }

    private fun checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
            openCamera()
        }
        else{
            cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            profilDetailedViewModel.uploadImages(imageBitmap)

        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(galleryIntent)
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImage = result.data?.data
            selectedImage?.let { profilDetailedViewModel.uploadImages(it) }
        }
    }

    private fun observes(){
        profilDetailedViewModel.selectedProfilImage.observe(viewLifecycleOwner){
            it?.let {
                if (it.isNotBlank()) {
                    profilDetailedViewModel.loadProfileImage()
                    Glide.with(this).load(it).into(binding.SelectedImage)
                    binding.SelectedImage.visibility = View.VISIBLE

                } else {
                    binding.SelectedImage.visibility = View.GONE
                }
            }
        }
        profilDetailedViewModel.imageVisibility.observe(viewLifecycleOwner){
            binding.SelectedImage.visibility = it
        }
        profilDetailedViewModel.containerVisibility.observe(viewLifecycleOwner){
            binding.defaultImage.visibility = it
        }

    }




}