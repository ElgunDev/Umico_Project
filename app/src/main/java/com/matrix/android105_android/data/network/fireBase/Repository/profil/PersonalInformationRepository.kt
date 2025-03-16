package com.matrix.android105_android.data.network.fireBase.Repository.profil

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.network.fireBase.repository.profil.IPersonalInformationRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PersonalInformationRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
):IPersonalInformationRepository {
    override suspend fun getPersonalInformation(uid: String): Result<PersonalInformations> {
        return try {
            val document = fireStore.collection("users").document(uid).get().await()
            if (document.exists()) {
                val name = document.getString("name") ?: ""
                val surName = document.getString("surName") ?: ""
                val phoneNumber = document.getString("phoneNumber") ?: ""
                val email = document.getString("email") ?: ""
                val fin = document.getString("fin") ?: ""
                val brithDay = document.getString("brithDay") ?: ""
                val gender = document.getString("gender") ?: ""
                val profileImageUrl = document.getString("profileImageUrl") ?: ""

                val personalInformations = PersonalInformations(
                    name = name,
                    surName = surName,
                    phoneNumber = phoneNumber,
                    email = email,
                    fin = fin,
                    brithDay = brithDay,
                    gender = gender,
                    profileImageUrl = profileImageUrl
                )

                Result.success(personalInformations)
            } else {
                Result.failure(Exception("No document found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

        override suspend fun updateProfilInformation(
            uid: String,
            personalInformations: PersonalInformations
        ): Result<Unit> {
            return try {
                fireStore.collection("users").document(uid).set(personalInformations).await()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

