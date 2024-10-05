package com.matrix.android105_android.data.Repository.Home.Products

import com.google.firebase.firestore.FirebaseFirestore
import com.matrix.android105_android.domain.Repository.Home.product.IProductRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductImplRepository @Inject constructor(
   private val fireStore: FirebaseFirestore

) :IProductRepository {
    override suspend fun getProduct(): List<Product> {
        val result = fireStore.collection("products").get().await()
        return result.documents.map {
            val imageUrl = it.getString("image")?:""
            val credit = it.getString("Credit")?:""
            val discountPrice =it.getString("discount_price")?:""
            val discountrate = it.getString("discount_rate")?:""
            val category = it.getString("category")?:""
            val name = it.getString("name")?:""
            val price = it.getString("price")?:""
            val rating = it.getString("rating")?:""
            Product(credit,category,discountPrice,discountrate,imageUrl,name,price,rating)
        }
    }
}