package com.cooperlighting.xyzdemotraining.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooperlighting.xyzdemotraining.R
import com.cooperlighting.xyzdemotraining.ProductsViewModel
import com.cooperlighting.xyzdemotraining.ShoppingCartRecyclerViewAdapter
import com.cooperlighting.xyzdemotraining.databinding.FragmentProductsCartBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Products Shopping Cart Class
 */
class ProductsCartFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val productsCartBinding = FragmentProductsCartBinding.bind(view)

        val recyclerView = productsCartBinding.recyclerView

        val productsAdapter = ShoppingCartRecyclerViewAdapter(productsViewModel)
        recyclerView.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }

        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val navController = NavHostFragment.findNavController(requireParentFragment())
                navController.popBackStack()
                navController.navigate(R.id.productsDetailViewFragment)

            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)

    }

}