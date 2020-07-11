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
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.fragment_detail_movie.detailOverview
import kotlinx.android.synthetic.main.fragment_detail_movie.detailPoster
import kotlinx.android.synthetic.main.fragment_detail_movie.detailRating
import kotlinx.android.synthetic.main.fragment_detail_movie.detailRelease
import kotlinx.android.synthetic.main.fragment_detail_movie.detailTitle
import kotlinx.android.synthetic.main.fragment_detail_tv_show.*

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
        setTvData(viewModel.getDetailTv(tvId))
    }

    private fun setTvData(tv: TvShow?) {
        detailTitleTv.text = tv?.name
        detailReleaseTv.text = tv?.firstAirDate
        detailRatingTv.text = tv?.voteAverage
        detailOverviewTv.text = tv?.overview
        tv?.posterPath?.let { detailPosterTv.setImageResource(it) }
    }


}
