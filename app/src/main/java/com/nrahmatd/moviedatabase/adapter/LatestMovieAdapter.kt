package com.nrahmatd.moviedatabase.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nrahmatd.moviedatabase.app.GlobalApp
import com.nrahmatd.moviedatabase.databinding.ItemBannerBinding
import com.nrahmatd.moviedatabase.model.BannerModel

class LatestMovieAdapter(
    private var bannerList: List<BannerModel.Data>
) : RecyclerView.Adapter<LatestMovieAdapter.ImageViewHolder>() {

    class ImageViewHolder(private val itemBannerBinding: ItemBannerBinding) : RecyclerView.ViewHolder(itemBannerBinding.root) {
        val image = itemBannerBinding.itemBannerIvImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder = ImageViewHolder(
        ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .centerCrop()
            .dontAnimate()
            .dontTransform()
            .priority(Priority.IMMEDIATE)
            .encodeFormat(Bitmap.CompressFormat.PNG)
            .format(DecodeFormat.DEFAULT)
        Glide.with(GlobalApp.getAppContext()).load(bannerList[position].bannerUrl).apply(requestOptions).into(holder.image)
    }
}
