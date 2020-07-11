package com.zeroemotion.bfaa_kotlin_tmdb.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.MovieItemBinding
import com.zeroemotion.bfaa_kotlin_tmdb.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.util.CustomOnClick
import com.zeroemotion.bfaa_kotlin_tmdb.util.getProgressDrawable
import com.zeroemotion.bfaa_kotlin_tmdb.util.loadImage
import com.zeroemotion.bfaa_kotlin_tmdb.view.MovieFragmentDirections
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), CustomOnClick {
    fun updateMovieList(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<MovieItemBinding>(inflater, R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = movieList[position]
        holder.view.listener = this
        holder.view.movieImageView.loadImage(
            movieList[position].posterPath,
            getProgressDrawable(holder.view.movieImageView.context)
        )

    }

    override fun onViewClicked(v: View) {
        val movieId = v.movieId.text.toString().toInt()
        val action = MovieFragmentDirections.actionDetailMovie()
        action.movieId = movieId
        Navigation.findNavController(v).navigate(action)
    }

    class MovieViewHolder(var view: MovieItemBinding) : RecyclerView.ViewHolder(view.root) {
    }


}