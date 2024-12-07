package com.matrix.android105_android.data.Network.fireBase.Repository.basketProduct

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.domain.Network.FireBase.Repository.basketProduct.IBasketProductRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BasketProductImplRepository @Inject constructor(
    private val fireStore:FirebaseFirestore
):IBasketProductRepository {
    override suspend fun addProduct(productId: String) {
        fireStore.collection("basket_product")
            .document(productId)
            .set(mapOf("productId" to productId))
            .await()
    }

    override suspend fun deleteProduct(productId: String) {
        fireStore.collection("basket_product")
            .document(productId)
            .delete()
            .await()
    }

    override suspend fun isProductBasket(productId: String): Boolean {
        val documentSnapshot =fireStore.collection("basket_product")
            .document(productId)
            .get()
            .await()
        return documentSnapshot.exists()
    }

    override suspend fun getBasketProduct(): List<Product> {
        val basketProductsIds = fireStore.collection("basket_product")
            .get()
            .await()
            .documents.mapNotNull { it.getString("productId") }
        if (basketProductsIds.isEmpty()) return emptyList()

        val productQuerySnapshot = fireStore.collection("products")
            .whereIn("id" , basketProductsIds)
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