package com.example.hemapalanisamy.kotlinsample.restclient

import retrofit2.http.GET

interface SOService {

    @GET("/photos")
    fun searchArtist(): io.reactivex.Observable<List<Photos>>
}