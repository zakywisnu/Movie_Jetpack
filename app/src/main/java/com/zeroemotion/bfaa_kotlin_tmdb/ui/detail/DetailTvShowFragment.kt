package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
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
    private lateinit var menu : Menu
    private lateinit var dataBinding: FragmentDetailTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
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
        viewModel.getTvDetail.observe(viewLifecycleOwner, Observer { tvs ->
            setTvData(tvs)
        })
    }

    private fun setTvData(tv: TvShowEntity?) {
        detailTitleTv.text = tv?.name
        detailReleaseTv.text = tv?.firstAirDate
        detailRatingTv.text = tv?.voteAverage
        detailOverviewTv.text = tv?.overview
        detailPosterTv.loadImage(tv?.posterPath, getProgressDrawable(detailPosterTv.context))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
        inflater.inflate(R.menu.favorite_menu, menu)
        viewModel.getTvDetail.observe(viewLifecycleOwner, Observer { response ->
            val isfavorite = response.favorite
            setFavoriteState(isfavorite)
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorites -> {
                viewModel.setFavoriteTvShow()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean){
        val menuItem = menu.findItem(R.id.favorites)
        context?.let { context ->
            if (state){
                menuItem?.icon = ContextCompat.getDrawable(context, R.drawable.ic_favorite)
            } else{
                menuItem?.icon = ContextCompat.getDrawable(context, R.drawable.ic_unfavorite)
            }
        }
    }
}
