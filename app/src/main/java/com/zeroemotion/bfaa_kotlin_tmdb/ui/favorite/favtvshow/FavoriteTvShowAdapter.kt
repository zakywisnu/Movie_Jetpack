package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favtvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.TvshowItemBinding
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.FavoriteFragmentDirections
import com.zeroemotion.bfaa_kotlin_tmdb.util.CustomOnClick
import com.zeroemotion.bfaa_kotlin_tmdb.util.getProgressDrawable
import com.zeroemotion.bfaa_kotlin_tmdb.util.loadImage
import kotlinx.android.synthetic.main.tvshow_item.view.*

class FavoriteTvShowAdapter() :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.TvShowViewHolder>(TvShowDiffCallback()),
    CustomOnClick {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TvshowItemBinding>(
            inflater,
            R.layout.tvshow_item,
            parent,
            false
        )
        return TvShowViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.view.tvShow = getItem(position) as TvShowEntity
        holder.view.listener = this
        holder.view.tvShowImageView.loadImage(
            getItem(position)?.posterPath,
            getProgressDrawable(holder.view.tvShowImageView.context)
        )
    }

    override fun onViewClicked(v: View) {
        val tvId = v.tvId.text.toString().toInt()
        val action = FavoriteFragmentDirections.actionDetailFavTvShow()
        action.tvId = tvId
        Navigation.findNavController(v).navigate(action)
    }

    class TvShowViewHolder(val view: TvshowItemBinding) : RecyclerView.ViewHolder(view.root)
    class TvShowDiffCallback : DiffUtil.ItemCallback<TvShowEntity>() {
        override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
            return oldItem == newItem
        }

    }
}


