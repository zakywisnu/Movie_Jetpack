package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentDetailMovieBinding
import com.zeroemotion.bfaa_kotlin_tmdb.util.getProgressDrawable
import com.zeroemotion.bfaa_kotlin_tmdb.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class DetailMovieFragment : Fragment() {

    private val args: DetailMovieFragmentArgs by navArgs()
    private lateinit var viewModel: DetailViewModel
    private var idMovie = 0
    private lateinit var menu : Menu
    private lateinit var dataBinding: FragmentDetailMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)
        idMovie = args.movieId
        viewModel = getViewModel { parametersOf(idMovie) }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getMovieDetail.observe(viewLifecycleOwner, Observer {
            setMovieData(it)
        })
    }

    private fun setMovieData(movie: MovieEntity?) {
        detailTitle.text = movie?.title
        detailRelease.text = movie?.releaseDate
        detailRating.text = movie?.voteAverage
        detailOverview.text = movie?.overview
        detailPoster.loadImage(movie?.posterPath, getProgressDrawable(detailPoster.context))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
        inflater.inflate(R.menu.favorite_menu, menu)
        viewModel.getMovieDetail.observe(viewLifecycleOwner, Observer { response ->
            val isfavorite = response.favorite
            setFavoriteState(isfavorite)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorites -> {
                viewModel.setFavoriteMovie()
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
