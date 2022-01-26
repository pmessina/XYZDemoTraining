package com.cooperlighting.xyzdemotraining.data

import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {

    @GET("/products")
    fun getProducts(): Call<ArrayList<Product>>
}