package com.matrix.android105_android.data.Network.fireBase.Repository.likedProduct

import androidx.lifecycle.MutableLiveData
import androidx.room.util.foreignKeyCheck
import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.domain.Network.FireBase.Repository.likedProduct.ILikedProductRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LikedProductImplRepository @Inject constructor(
    private  val fireStore: FirebaseFirestore
):ILikedProductRepository {
    override suspend fun addProduct(productId: String) {
        fireStore.collection("liked_products")
            .document(productId)
            .set(mapOf("productId" to productId))
            .await()
    }

    override suspend fun deleteProduct(productId: String) {
        fireStore.collection("liked_products")
            .document(productId)
            .delete()
            .await()
    }

    override suspend fun isProductLiked(productId: String): Boolean {
        val documentSnapshot = fireStore.collection("liked_products")
            .document(productId)
            .get()
            .await()
        return documentSnapshot.exists()
    }

    override suspend fun getLikedProducts(): List<Product> {
        val likedProductIds = fireStore.collection("liked_products")
            .get()
            .await()
            .documents.mapNotNull { it.getString("productId") }

        if (likedProductIds.isEmpty()) return emptyList()


        val productQuerySnapshot = fireStore.collection("products")
            .whereIn("id", likedProductIds)
            .get()
            .await()

        return productQuerySnapshot.documents.map {
            val id = it.getString("id") ?: ""
            val imageUrl = it.getString("image") ?: ""
            val credit = it.getString("Credit") ?: ""
            val discountPrice = (it.get("discount_price") as? Double) ?: 0.0
            val discountrate = it.getString("discount_rate") ?: ""
            val category = it.getString("category") ?: ""
            val name = it.getString("name") ?: ""
            val price = (it.get("price") as? Double) ?: 0.0
            val rating = it.getString("rating") ?: ""
            val companyName = it.getString("company_name") ?: ""
            val companyLogo = it.getString("company_logo") ?: ""
            val stock = (it.get("stock") as? Long) ?: 0L
            val quantity = (it.get("quantity") as? Int) ?: 1
            Product(
                id,
                credit,
                category,
                discountPrice,
                discountrate,
                imageUrl,
                name,
                price,
                rating,
                companyName,
                companyLogo,
                null,
                stock,
                quantity
            )
        }
    }
}