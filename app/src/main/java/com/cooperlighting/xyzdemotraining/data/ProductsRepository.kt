package com.cooperlighting.xyzdemotraining.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsRepository {

    private var productsApi: ProductsApi

    init {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productsApi = retrofit.create(ProductsApi::class.java)
    }

    fun getProducts(): MutableLiveData<ArrayList<Product>> {

        val contactsData = MutableLiveData<ArrayList<Product>>()

        productsApi.getProducts().enqueue(object : Callback<ArrayList<Product>> {
            override fun onResponse(call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>) {
                contactsData.value = response.body()
                Log.i("ProductsApi", response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return contactsData
    }
}