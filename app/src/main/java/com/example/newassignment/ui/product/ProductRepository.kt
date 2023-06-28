package com.example.newassignment.ui.product

import com.example.newassignment.room.ProductDaoInterface
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val postDao: ProductDaoInterface) {

    suspend fun insertPosts(posts: List<ProductModellItem>) {
        postDao.insertFavoriteProduct(posts)
    }
}