package com.matrix.android105_android.domain.UseCase.Home.products

import com.matrix.android105_android.data.Network.fireBase.Repository.Home.Products.Product
import com.matrix.android105_android.domain.Network.FireBase.Repository.Home.product.IAllProductRepository
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val allProductRepository: IAllProductRepository
):IGetAllProductUseCase{
    override suspend fun getAllProducts(loadMore: Boolean, callback: (Result<List<Product>>) -> Unit) {
        allProductRepository.getAllProducts(loadMore, callback)
    }


}