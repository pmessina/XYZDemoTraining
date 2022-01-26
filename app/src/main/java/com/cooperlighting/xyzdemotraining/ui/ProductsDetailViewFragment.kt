package com.cooperlighting.xyzdemotraining.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cooperlighting.xyzdemotraining.R
import com.cooperlighting.xyzdemotraining.ProductsViewModel
import com.cooperlighting.xyzdemotraining.data.Product
import com.cooperlighting.xyzdemotraining.databinding.ProductsDetailViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.InputStream
import java.net.URL


/**
 * Products Details class
 */

/*
a. The name (title) of the product.
b. The price of the product
c. A mini description of the product in at most 2 lines
d. An image of the product
e. API for list of products: https://fakestoreapi.com/products
*/

class ProductsDetailViewFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by viewModel()

    private var currentProduct: Product = Product()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ProductsDetailViewBinding.inflate(inflater, container, false).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ProductsDetailViewBinding.bind(view)

        currentProduct = productsViewModel.selectedProduct

        binding.tvDVPrice.text = currentProduct.price.toString()
        binding.tvDVProductDescription.text = currentProduct.description
        binding.tvDVProductName.text = currentProduct.title

        runBlocking {

            val d = getDrawableFromURL()
            //TODO: Refactor image name
            Glide.with(requireActivity())
                .load(d)
                .error(R.drawable.ic_image_placeholder)
                .apply(RequestOptions().override(300, 300))
                .into(binding.imageView)
        }

        binding.btnDVAddToCart.setOnClickListener {
            val cartDialogFragment = CartDialogFragment()
            cartDialogFragment.show(childFragmentManager, "dialog")
        }

    }

    private suspend fun getDrawableFromURL() = coroutineScope {
        val drawable = async<Drawable>(Dispatchers.IO) {
            val stream: InputStream = URL(currentProduct.image).content as InputStream
            Drawable.createFromStream(stream, "$currentProduct.title")
        }

        drawable.await()
    }


}