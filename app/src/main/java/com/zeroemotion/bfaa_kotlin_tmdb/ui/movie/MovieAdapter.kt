package com.zeroemotion.bfaa_kotlin_tmdb.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.MovieItemBinding
import com.zeroemotion.bfaa_kotlin_tmdb.util.CustomOnClick
import com.zeroemotion.bfaa_kotlin_tmdb.util.getProgressDrawable
import com.zeroemotion.bfaa_kotlin_tmdb.util.loadImage
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter() : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(MovieDiffCallback()), CustomOnClick {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<MovieItemBinding>(inflater, R.layout.movie_item, parent, false)
        return MovieViewHolder(
            view
        )
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = getItem(position) as MovieEntity
        holder.view.listener = this
        holder.view.movieImageView.loadImage(
            getItem(position)?.posterPath,
            getProgressDrawable(holder.view.movieImageView.context)
        )

    }

    override fun onViewClicked(v: View) {
        val movieId = v.movieId.text.toString().toInt()
        val action = MovieFragmentDirections.actionDetailMovie()
        action.movieId = movieId
        Navigation.findNavController(v).navigate(action)
    }

    class MovieViewHolder(var view: MovieItemBinding) : RecyclerView.ViewHolder(view.root)

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }

    }


}