package com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IProductRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductImplRepository @Inject constructor(
   private val fireStore: FirebaseFirestore

) : IProductRepository {
    override suspend fun getProduct(): List<Product> {
        val result = fireStore.collection("products").get().await()
        return result.documents.map {
            val id = it.getString("id") ?: ""
            val imageUrl = it.getString("image") ?: ""
            val credit = it.getString("Credit") ?: ""
            val discountPrice = (it.get("discount_price") as? Double) ?: 0.0
            val discountrate = it.getString("discount_rate") ?: ""
            val category = it.getString("category") ?: ""
            val name = it.getString("name") ?: ""
            val price =  (it.get("price") as? Double) ?: 0.0
            val rating = it.getString("rating") ?: ""
            val companyName = it.getString("company_name") ?: ""
            val companyLogo = it.getString("company_logo") ?: ""
            val stock = (it.get("stock") as? Long) ?: 0L
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
                stock
            )
        }
    }

    override suspend fun updateProductStock(productId: String, newStock: Long) {
        val docRef = fireStore.collection("products").document(productId)
        val documentSnapshot = docRef.get().await()
        if (documentSnapshot.exists()) {
            docRef.update("stock", newStock).await()
        }
    }
}