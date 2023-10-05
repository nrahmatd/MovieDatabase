package com.nrahmatd.moviedatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nrahmatd.moviedatabase.app.GlobalApp
import com.nrahmatd.moviedatabase.databinding.ItemBannerBinding
import com.nrahmatd.moviedatabase.databinding.ItemTrendingMovieBinding
import com.nrahmatd.moviedatabase.model.BannerModel

class TrendingMovieAdapter(
    private var bannerList: List<BannerModel.Data>
) : RecyclerView.Adapter<TrendingMovieAdapter.ImageViewHolder>() {

    class ImageViewHolder(private val itemTrendingMovieBinding: ItemTrendingMovieBinding) : RecyclerView.ViewHolder(itemTrendingMovieBinding.root) {
        val image = itemTrendingMovieBinding.ivBanner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder = ImageViewHolder(
        ItemTrendingMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(GlobalApp.getAppContext()).load(bannerList[position].bannerUrl).into(holder.image)
    }
}
