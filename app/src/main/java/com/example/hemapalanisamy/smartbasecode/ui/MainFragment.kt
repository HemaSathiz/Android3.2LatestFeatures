package com.example.hemapalanisamy.smartbasecode.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.hemapalanisamy.smartbasecode.R
import com.example.hemapalanisamy.smartbasecode.databinding.MainFragmentBinding
import com.example.hemapalanisamy.smartbasecode.ui.main.viewmodel.MainViewModel

class MainFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding : MainFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.callImagesApi()
    }

}
