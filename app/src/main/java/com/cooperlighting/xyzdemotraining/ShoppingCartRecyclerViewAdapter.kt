package com.cooperlighting.xyzdemotraining

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cooperlighting.xyzdemotraining.databinding.ProductsListViewItemBinding
import java.text.DecimalFormat

class ShoppingCartRecyclerViewAdapter(private val productsViewModel: ProductsViewModel) :
    RecyclerView.Adapter<ShoppingCartRecyclerViewAdapter.ProductViewHolder>() {

    private var products = productsViewModel.selectedProducts


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val decFormat = DecimalFormat("$###.##")
        val price = decFormat.format(products[position].price)

        holder.binding.tvPrice.text = price
        holder.binding.tvProductName.text = products[position].title
        holder.binding.root.tag = products[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductsListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }


    inner class ProductViewHolder(val binding: ProductsListViewItemBinding) : RecyclerView.ViewHolder(binding.root)
}