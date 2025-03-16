package com.matrix.android105_android.domain.useCase.home.products

import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.domain.network.fireBase.repository.home.product.IAllProductRepository
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val allProductRepository: IAllProductRepository
):IGetAllProductUseCase{
    override suspend fun getAllProducts(loadMore: Boolean, callback: (Result<List<Product>>) -> Unit) {
        allProductRepository.getAllProducts(loadMore, callback)
    }


}