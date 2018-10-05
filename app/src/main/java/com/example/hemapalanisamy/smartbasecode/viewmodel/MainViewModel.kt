package com.example.hemapalanisamy.smartbasecode.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hemapalanisamy.kotlinsample.restclient.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainViewModel : ViewModel() {



    fun callImagesApi() {
        val compositeDisposable: CompositeDisposable = CompositeDisposable()
        val repository = ApiUtils.getSOService()
        compositeDisposable.add(
                repository.searchArtist()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())

                        .subscribe({ result ->

                            Log.e("Test",result.size.toString())
                        }, { error ->

                        })
        )


    }


}
