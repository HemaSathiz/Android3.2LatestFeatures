package com.example.hemapalanisamy.kotlinsample.restclient


class ApiUtils {

    companion object {

        fun getSOService(): SOService {
            return RetrofitClient.getClient("https://jsonplaceholder.typicode.com/")!!.create(SOService::class.java)
        }
    }

}
