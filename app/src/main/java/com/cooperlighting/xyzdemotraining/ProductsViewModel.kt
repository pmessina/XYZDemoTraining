package com.cooperlighting.xyzdemotraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cooperlighting.xyzdemotraining.data.Product
import com.cooperlighting.xyzdemotraining.data.ProductsRepository

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    //used to populate all products in the recycler view
    var products = ArrayList<Product>()

    //Used for detailed view
    var selectedProduct = Product()

    //used to hold all the products added to the cart
    var selectedProducts = ArrayList<Product>()


    fun getProductsRepository(): LiveData<ArrayList<Product>> {
        return productsRepository.getProducts()
    }


}

