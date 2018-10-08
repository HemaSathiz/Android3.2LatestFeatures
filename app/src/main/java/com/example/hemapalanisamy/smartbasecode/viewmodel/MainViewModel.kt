package com.example.hemapalanisamy.smartbasecode.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hemapalanisamy.kotlinsample.restclient.ApiUtils
import com.example.hemapalanisamy.kotlinsample.restclient.Photos
import com.example.hemapalanisamy.smartbasecode.ui.main.MainFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException


class MainViewModel : ViewModel() {

    val listPhotos : MutableLiveData<List<Photos>> = MutableLiveData<List<Photos>>()
    var blogObservableArrayList: ObservableList<Photos> = ObservableArrayList()

    fun addBlogItemsToList(blogs: List<Photos>) {
        blogObservableArrayList.clear()
        blogObservableArrayList.addAll(blogs)
    }
    fun callImagesApi(mainFragment: MainFragment) {
        val compositeDisposable: CompositeDisposable = CompositeDisposable()
        val repository = ApiUtils.getSOService()
        compositeDisposable.add(
                repository.searchArtist()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            listPhotos.value=result
                        }, { error ->
                            loadError(mainFragment,error)
                        })
        )
    }

    fun getAllPhotos(): MutableLiveData<List<Photos>> {
        return listPhotos
    }
    fun loadError(fragment: MainFragment, error: Throwable) {

        try {

            if(error is HttpException){
                val httpException = error
                fragment.onErrorHandle(error.toString())
            }

            if(error is IOException){
                fragment.onErrorHandle(error.toString())
            }

        } catch (e: Exception) {
            fragment.onErrorHandle(error.toString())
        }


    }
}
