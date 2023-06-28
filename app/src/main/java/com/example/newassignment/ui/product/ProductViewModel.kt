package com.example.newassignment.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newassignment.network.ApiInterface
import com.example.newassignment.room.ProductDaoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class ProductViewModel(
    private val apiInterface: ApiInterface,
    private val productRepository: ProductRepository,
    private val favoriteProductDao: ProductDaoInterface
) : ViewModel() {
    private val _productsLiveData = MutableLiveData<List<ProductModellItem>>()
    val productsLiveData: LiveData<List<ProductModellItem>> = _productsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private var isProductsFetched = false

    fun fetchProducts() {
        if (!isProductsFetched) {
            viewModelScope.launch {
                try {
                    _loadingLiveData.value = true
                    val result = apiInterface.getProducts()

                    val products = result

                    _productsLiveData.value = products
                    isProductsFetched = true

                    products?.let {
                        productRepository.insertPosts(it)
                    }

                } catch (e: Exception) {
                    Log.e("TAG", "fetchProducts: " + e)
                } finally {
                    _loadingLiveData.value = false
                }
            }
        }
    }
}

