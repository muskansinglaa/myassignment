package com.example.newassignment.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.newassignment.databinding.ItemProductBinding
import com.example.newassignment.room.ProductDaoInterface
import com.example.newassignment.ui.productDetail.ProductItem

class ProductAdapter(private val fragmentManager: FragmentManager, var products: List<ProductModellItem>, private val context: Context, private val favoriteProductDao: ProductDaoInterface, private val lifecycleScope: LifecycleCoroutineScope) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
//    private val productItems = mutableListOf<ProductModellItem>()
    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        onItemClickCallback = callback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductModellItem) {
            binding.titleTextView.text = "${product.title}"


            binding.root.setOnClickListener {
                val product = ProductItem(
                    id = product.id.toString(),
                    title = product.title.toString(),
                    body  =product.body.toString()
                )
                onItemClickCallback.onItemClick(product)
            }
        }

    }


    interface OnItemClickCallback {
        fun onItemClick(products: ProductItem)
    }
}
