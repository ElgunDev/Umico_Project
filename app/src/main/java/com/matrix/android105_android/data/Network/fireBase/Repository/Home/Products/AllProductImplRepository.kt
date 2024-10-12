package com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IAllProductRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AllProductImplRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
): IAllProductRepository {

    private var lastVisibilityProduct: DocumentSnapshot?=null
    private val pageSize = 4
    override suspend fun getAllProducts(loadMore: Boolean, callback: (Result<List<Product>>) -> Unit) {
        try {

            var query = fireStore.collection("products").limit(pageSize.toLong())

            if (loadMore && lastVisibilityProduct != null) {
                query = query.startAfter(lastVisibilityProduct!!)
            }
            val result: QuerySnapshot = query.get().await()


            if (!result.isEmpty) {
                val products = result.documents.map {
                    val id = it.getString("id")?:""
                    val imageUrl = it.getString("image") ?: ""
                    val credit = it.getString("Credit") ?: ""
                    val discountPrice = it.getString("discount_price") ?: ""
                    val discountRate = it.getString("discount_rate") ?: ""
                    val category = it.getString("category") ?: ""
                    val name = it.getString("name") ?: ""
                    val price = it.getString("price") ?: ""
                    val rating = it.getString("rating") ?: ""

                    Product(
                        id,
                        credit,
                        category,
                        discountPrice,
                        discountRate,
                        imageUrl,
                        name,
                        price,
                        rating
                    )
                }
                lastVisibilityProduct = result.documents[result.size() - 1]

                callback(Result.success(products))
            } else {
                callback(Result.success(emptyList()))
            }

        }
        catch (e:Exception){
            println("ASDASGDACAGDASFDASFSAFAFSFDAS")
            callback(Result.failure(e))
        }




    }
}