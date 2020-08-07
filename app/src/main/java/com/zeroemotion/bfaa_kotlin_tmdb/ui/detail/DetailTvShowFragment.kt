package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.Status
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentDetailTvShowBinding
import com.zeroemotion.bfaa_kotlin_tmdb.util.getProgressDrawable
import com.zeroemotion.bfaa_kotlin_tmdb.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail_tv_show.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class DetailTvShowFragment : Fragment() {

    private val args: DetailTvShowFragmentArgs by navArgs()
    private lateinit var viewModel: DetailViewModel
    private var idTv = 0
    private lateinit var dataBinding: FragmentDetailTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_tv_show, container, false)
        idTv = args.tvId
        viewModel = getViewModel { parametersOf(idTv) }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getTvDetail().observe(viewLifecycleOwner, Observer { state ->
            if (state != null) {
                when (state.status) {
                    Status.SUCCESS -> setTvData(state.data)
                }
            }
        })
    }

    private fun setTvData(tv: TvShow?) {
        detailTitleTv.text = tv?.name
        detailReleaseTv.text = tv?.firstAirDate
        detailRatingTv.text = tv?.voteAverage
        detailOverviewTv.text = tv?.overview
        detailPosterTv.loadImage(tv?.posterPath, getProgressDrawable(detailPosterTv.context))
    }


}
