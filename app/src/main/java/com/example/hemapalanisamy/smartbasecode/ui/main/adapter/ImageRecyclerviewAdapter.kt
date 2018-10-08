package com.example.hemapalanisamy.smartbasecode.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.hemapalanisamy.kotlinsample.restclient.Photos
import com.example.hemapalanisamy.smartbasecode.R
import com.example.hemapalanisamy.smartbasecode.databinding.ItemlistPhotosBinding
import com.example.hemapalanisamy.smartbasecode.ui.main.viewmodel.ItemListPhotosViewModel


class ImageRecyclerviewAdapter :
        RecyclerView.Adapter<ImageRecyclerviewAdapter.ViewHolder>() {

    private  val myDataset: ArrayList<Photos> =ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ImageRecyclerviewAdapter.ViewHolder {
        val binding : ItemlistPhotosBinding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.itemlist_photos, parent, false) as ItemlistPhotosBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photos= myDataset.get(position)
        holder.bind(photos)
    }


    override fun getItemCount(): Int {
        return myDataset.size
    }


    fun addItems(blogList: List<Photos>) {
        myDataset.addAll(blogList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        myDataset.clear()
    }

    inner class ViewHolder(var itemListBinding: ItemlistPhotosBinding) : RecyclerView.ViewHolder(itemListBinding.root) {

        private lateinit var mBlogItemViewModel: ItemListPhotosViewModel

        fun bind(photos: Photos) {
            mBlogItemViewModel = ItemListPhotosViewModel(photos)
            itemListBinding.user = mBlogItemViewModel
            itemListBinding.executePendingBindings()

        }
    }
}



