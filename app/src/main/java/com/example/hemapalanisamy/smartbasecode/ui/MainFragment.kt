package com.example.hemapalanisamy.smartbasecode.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.hemapalanisamy.kotlinsample.restclient.Photos
import com.example.hemapalanisamy.smartbasecode.R
import com.example.hemapalanisamy.smartbasecode.databinding.MainFragmentBinding
import com.example.hemapalanisamy.smartbasecode.ui.MainNavigator
import com.example.hemapalanisamy.smartbasecode.ui.adapter.RecyclerviewAdapter
import com.example.hemapalanisamy.smartbasecode.utils.AppConstant
import com.example.hemapalanisamy.smartbasecode.utils.InfiniteCarouselTransformer
import com.example.hemapalanisamy.smartbasecode.viewmodel.MainViewModel
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter


class MainFragment : androidx.fragment.app.Fragment(),MainNavigator, DiscreteScrollView.OnItemChangedListener<RecyclerviewAdapter.ViewHolder> {
    override fun onCurrentItemChanged(p0: RecyclerviewAdapter.ViewHolder?, p1: Int) {

        val realPosition = mInfiniteScrollWrapper.realCurrentPosition

    }


    private lateinit var viewAdapter: RecyclerviewAdapter
    private lateinit var viewModel: MainViewModel
    lateinit var binding: MainFragmentBinding
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mInfiniteScrollWrapper: InfiniteScrollAdapter<*>

    companion object {
        private lateinit var status: String

        fun newInstance(status: String): MainFragment {
            val fragment = MainFragment()
            this.status=status
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        if (status.equals(AppConstant.linearRecyclerView)) {
            viewManager = LinearLayoutManager(activity)
        } else if (status.equals(AppConstant.gridRecyclerView)) {
            viewManager = GridLayoutManager(activity, 2)
        }else if (status.equals(AppConstant.staggeredRecyclerView)) {
            viewManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }else if(status.equals(AppConstant.carouselRecyclerView)){

        }


        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //binding.recyclerView.visibility=View.VISIBLE
        viewModel.callImagesApi(this)
        setUp()
        subscribeLiveData()


    }


    fun setUp() {
        viewAdapter = RecyclerviewAdapter()
        mInfiniteScrollWrapper = InfiniteScrollAdapter.wrap(viewAdapter)
        binding.defaultitem.adapter = mInfiniteScrollWrapper

        // Item transformer
        binding.defaultitem.setItemTransformer(InfiniteCarouselTransformer())

        // Item change listener
        binding.defaultitem.addOnItemChangedListener(this)


        binding.recyclerView.layoutManager = viewManager
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = viewAdapter
        binding.user = viewModel



    }

    fun subscribeLiveData() {
        viewModel.getAllPhotos().observe(this, object : Observer<List<Photos>> {
            override fun onChanged(photos: List<Photos>) {

                viewModel.addBlogItemsToList(photos)
                binding.progressBar.visibility=View.GONE

            }

        })

    }
    override fun onErrorHandle(s: String) {
       Toast.makeText(activity,s,Toast.LENGTH_LONG).show()
    }

}
