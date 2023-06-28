package com.example.newassignment.ui.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newassignment.databinding.FragmentFavouriteBinding
import com.example.newassignment.room.ProductDaoInterface
import org.koin.android.ext.android.inject

class ProductDetailFragment : Fragment() {
    companion object {
        private const val ARG_DATA = "arg_data"

        fun newInstance(data: ProductItem): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteProductDao: ProductDaoInterface by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val data = arguments?.getParcelable<ProductItem>(ARG_DATA)
        binding.titleTextView.text = data?.title
        binding.descriptionTextView.text = data?.body
        return binding.root
    }
}