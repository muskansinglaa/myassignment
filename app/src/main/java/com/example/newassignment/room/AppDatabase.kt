package com.example.newassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newassignment.ui.product.ProductModellItem


@Database(entities = [ProductModellItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): ProductDaoInterface
}
