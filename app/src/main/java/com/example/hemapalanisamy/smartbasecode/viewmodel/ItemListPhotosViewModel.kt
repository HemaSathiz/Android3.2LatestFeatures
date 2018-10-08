package com.example.hemapalanisamy.smartbasecode.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.example.hemapalanisamy.kotlinsample.restclient.Photos

class ItemListPhotosViewModel( photos: Photos) : BaseObservable() {

 var imageUrl: ObservableField<String> = ObservableField()

 init {
  imageUrl.set(photos.url)
 }


}