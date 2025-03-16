package com.matrix.android105_android.data.network.fireBase.Repository.home.products

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.matrix.android105_android.domain.network.fireBase.repository.home.product.IAllProductRepository
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
                    val discountPrice = (it.get("discount_price") as? Double) ?:0.0
                    val discountRate = it.getString("discount_rate") ?: ""
                    val category = it.getString("category") ?: ""
                    val name = it.getString("name") ?: ""
                    val price = (it.get("price") as? Double) ?:0.0
                    val rating = it.getString("rating") ?: ""
                    val companyName = it.getString("company_name")?:""
                    val companyLogo = it.getString("company_logo")?:""
                    val stock = (it.get("stock") as? Long)?:0L

                    Product(
                        id,
                        credit,
                        category,
                        discountPrice,
                        discountRate,
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