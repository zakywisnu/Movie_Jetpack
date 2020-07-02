package com.zeroemotion.bfaa_kotlin_tmdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentDetailTvShowBinding
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.DetailViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailTvShowFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var tvId = 0
    private lateinit var dataBinding: FragmentDetailTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_tv_show,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            tvId = DetailTvShowFragmentArgs.fromBundle(it).tvId
        }
        viewModel.fetchTV(tvId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.tvLiveData.observe(viewLifecycleOwner, Observer { tvDetail ->
            tvDetail?.let {
                dataBinding.tvShow = tvDetail
            }
        })
    }

}
