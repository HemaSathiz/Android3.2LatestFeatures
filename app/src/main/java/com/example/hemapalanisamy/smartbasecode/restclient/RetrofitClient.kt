package com.example.hemapalanisamy.kotlinsample.restclient

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {


    private var retrofit: Retrofit? = null
    companion object {
         fun getClient(baseUrl: String): Retrofit? {

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build()


            return retrofit
        }
    }


}