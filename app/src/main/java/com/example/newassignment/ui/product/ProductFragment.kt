package com.example.newassignment.ui.product

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newassignment.R
import com.example.newassignment.databinding.FragmentProductBinding
import com.example.newassignment.room.ProductDaoInterface
import com.example.newassignment.ui.productDetail.ProductDetailFragment
import com.example.newassignment.ui.productDetail.ProductItem
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by viewModel()
    private lateinit var productAdapter: ProductAdapter
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val favoriteProductDao: ProductDaoInterface by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter(childFragmentManager,emptyList(), requireContext(), favoriteProductDao, lifecycleScope)
        binding.productRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.productRecyclerView.adapter = productAdapter
        productViewModel.fetchProducts()
binding.whatsapp.setOnClickListener {
    openWhatsApp()
}
        productViewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.productRecyclerView.visibility = View.GONE
                binding.progressBarId.visibility = View.VISIBLE
            } else {
                binding.productRecyclerView.visibility = View.VISIBLE
                binding.progressBarId.visibility = View.GONE
            }
        }

        productViewModel.productsLiveData.observe(viewLifecycleOwner) { products ->

            productAdapter.products = products
            productAdapter.notifyDataSetChanged()
        }
        productAdapter.setOnItemClickCallback(object:ProductAdapter.OnItemClickCallback{
            override fun onItemClick(product:ProductItem) {
                val favouriteFragment = ProductDetailFragment.newInstance(product)
                // Replace the current fragment with the second fragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, favouriteFragment)
                    .addToBackStack(null)
                    .commit()
            }

        })

    }
    fun openWhatsApp() {
        val whatsappPackage = "com.whatsapp"
        val playStoreUrl = "https://play.google.com/store/apps/details?id=$whatsappPackage"

        if (isWhatsAppInstalled(requireContext())) {
            val intent = requireActivity().packageManager.getLaunchIntentForPackage(whatsappPackage)
            startActivity(intent)
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(playStoreUrl))
            startActivity(intent)
        }

    }
    fun isWhatsAppInstalled(context: Context): Boolean {

        val packageManager = context.packageManager
        return try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}