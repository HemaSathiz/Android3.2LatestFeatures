package com.example.hemapalanisamy.kotlinsample.restclient

import java.util.*

class Photos : Observable() {
    var id: String = " "
        set(value) {
            field = value
            setChangedAndNotify("firstName")
        }


    var title: String = " "
        set(value) {
            field = value
            setChangedAndNotify("title")
        }


    var url: String = " "
        set(value) {
            field = value
            setChangedAndNotify("url")
        }


    var thumbnailUrl: String = " "
        set(value) {
            field = value
            setChangedAndNotify("thumbnailUrl")
        }


    private fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}
