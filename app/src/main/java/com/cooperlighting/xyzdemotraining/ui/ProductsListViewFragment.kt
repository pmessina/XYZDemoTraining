package com.cooperlighting.xyzdemotraining.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooperlighting.xyzdemotraining.contactsviewer.ProductsRecyclerViewAdapter
import com.cooperlighting.xyzdemotraining.databinding.ProductsListViewContainerBinding
import com.cooperlighting.xyzdemotraining.ProductsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Products ListView Class
 */
class ProductsListViewFragment : Fragment() {


    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = ProductsListViewContainerBinding.inflate(inflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ProductsListViewContainerBinding.bind(view)
        val context = binding.root.context
        val recyclerView = binding.productsRecyclerView

        productsViewModel.getProductsRepository().observe(this.viewLifecycleOwner, { t ->

            productsViewModel.products = t

            val productsAdapter = ProductsRecyclerViewAdapter(productsViewModel)
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

        })
    }

}