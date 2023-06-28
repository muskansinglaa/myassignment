package com.example.newassignment.ui.product

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize

data class ProductModell(

	val productModell: List<ProductModellItem>? = null
) : Parcelable

@Parcelize
@Entity(tableName = "favourite_products")
data class ProductModellItem(
	@PrimaryKey
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
) : Parcelable
