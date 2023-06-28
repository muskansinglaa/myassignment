package com.example.newassignment.network

import com.example.newassignment.ui.product.ProductModellItem
import retrofit2.http.GET
interface ApiInterface {
    @GET("posts")
    suspend fun getProducts(): List<ProductModellItem>
}