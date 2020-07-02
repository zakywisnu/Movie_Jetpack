package com.zeroemotion.bfaa_kotlin_tmdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.TvshowItemBinding
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.util.CustomOnClick
import com.zeroemotion.bfaa_kotlin_tmdb.util.getProgressDrawable
import com.zeroemotion.bfaa_kotlin_tmdb.util.loadImage
import com.zeroemotion.bfaa_kotlin_tmdb.view.MovieFragmentDirections
import com.zeroemotion.bfaa_kotlin_tmdb.view.TvShowFragmentDirections
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.tvshow_item.view.*

class TvShowAdapter (val tvShowList: ArrayList<TvShow>): RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>(), CustomOnClick{

    fun updateTvList(newTvList: List<TvShow>){
        tvShowList.clear()
        tvShowList.addAll(newTvList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TvshowItemBinding>(inflater, R.layout.tvshow_item,parent,false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount()=tvShowList.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.view.tvShow = tvShowList[position]
        holder.view.listener = this
        holder.view.tvShowImageView.loadImage(
            tvShowList[position].posterPath,
            getProgressDrawable(holder.view.tvShowImageView.context)
        )
    }
    override fun onViewClicked(v: View) {
        val tvId = v.tvId.text.toString().toInt()
        val action = TvShowFragmentDirections.actionDetailTvShow()
        action.tvId = tvId
        Navigation.findNavController(v).navigate(action)
    }
    class TvShowViewHolder(val view: TvshowItemBinding): RecyclerView.ViewHolder(view.root)



}