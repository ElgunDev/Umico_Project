package com.matrix.android105_android.data.network.fireBase.Repository.profil

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.matrix.android105_android.domain.network.fireBase.repository.profil.IProfilImagesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ProfilImagesRepository @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseStorage:FirebaseStorage,
)
    :IProfilImagesRepository {
    override suspend fun uploadProfilImages(images:Any, uid: String):Result<Unit> {
        return try {
            val storageRef =  firebaseStorage.getReference()
            val imageRef= storageRef.child("profil_images/$uid.jpg")
            when (images) {
                is Bitmap -> {
                    val baos = ByteArrayOutputStream()
                    images.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()
                   val uploadTask =imageRef.putBytes(data)
                    uploadTask.addOnSuccessListener {
                        imageRef.downloadUrl.addOnSuccessListener {uri->
                            CoroutineScope(Dispatchers.IO).launch {
                                val downloadUrl = uri.toString()
                                fireStore.collection("users").document(uid)
                                    .update("profileImageUrl",downloadUrl).await()
                            }
                        }
                    }
                        .addOnFailureListener{exception->
                            Log.e("Firebase", "Image upload failed: ${exception.message}")
                        }
                }

                is Uri -> {
                    val uploadTask = imageRef.putFile(images)
                    uploadTask.addOnSuccessListener {
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            CoroutineScope(Dispatchers.IO).launch {
                                val downloadUrl = uri.toString() // Correctly get the download URL
                                fireStore.collection("users").document(uid)
                                    .update("profileImageUrl", downloadUrl).await()
                            }
                        }
                    }.addOnFailureListener { exception ->
                        Log.e("Firebase", "Image upload failed: ${exception.message}")
                    }
                }
                else -> throw IllegalArgumentException("Unsupported image type")
            }

            Result.success(Unit)
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }


    override suspend fun getProfilImages(uid: String): Result<String> {
        return try {
            val document = fireStore.collection("users").document(uid).get().await()
            val url =  document.getString("profileImageUrl") ?: ""
            Result.success(url)
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }

    }

