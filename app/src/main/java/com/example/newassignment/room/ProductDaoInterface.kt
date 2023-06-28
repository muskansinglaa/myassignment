package com.example.newassignment.room

import androidx.room.*
import com.example.newassignment.ui.product.ProductModellItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDaoInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteProduct(favouriteProduct: List<ProductModellItem>)

    @Query("SELECT * FROM favourite_products")
    fun getAllFavoriteProducts(): Flow<List<ProductModellItem>>
}
