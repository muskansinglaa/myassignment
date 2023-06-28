package com.example.newassignment.ui.productDetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(val id: String, val title: String, val body:String): Parcelable