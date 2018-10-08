package com.example.hemapalanisamy.smartbasecode.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hemapalanisamy.kotlinsample.restclient.Photos
import com.example.hemapalanisamy.smartbasecode.ui.main.adapter.ImageRecyclerviewAdapter

class BindingUtils {
    companion object {

        @JvmStatic @BindingAdapter("adapter")

        fun addBlogItems(recyclerView: RecyclerView, blogs: List<Photos>?) {
            val adapter = recyclerView.adapter as ImageRecyclerviewAdapter
            if (adapter != null && blogs!=null && !blogs.isEmpty()) {
                adapter.clearItems()
                adapter.addItems(blogs)
            }
        }


        @JvmStatic @BindingAdapter("app:imageResource")
        fun loadImage(view: ImageView, url: String) {
            if ( url!=null && !url.isEmpty()) {
                Glide.with(view).load(url).into(view)
            }
        }
    }

}