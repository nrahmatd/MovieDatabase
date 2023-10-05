package com.nrahmatd.moviedatabase.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.nrahmatd.moviedatabase.adapter.LatestMovieAdapter
import com.nrahmatd.moviedatabase.adapter.SeriesMovieAdapter
import com.nrahmatd.moviedatabase.adapter.TrendingMovieAdapter
import com.nrahmatd.moviedatabase.baseview.BaseFragment
import com.nrahmatd.moviedatabase.databinding.FragmentHomeBinding
import com.nrahmatd.moviedatabase.model.BannerModel
import com.nrahmatd.moviedatabase.model.MovieModel
import com.nrahmatd.moviedatabase.utils.ResponseHelper
import com.nrahmatd.moviedatabase.utils.toast
import com.nrahmatd.moviedatabase.viewmodel.MovieViewModel
import com.nrahmatd.moviedatabase.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var latestMovieAdapter: LatestMovieAdapter
    private lateinit var seriesMovieAdapter: SeriesMovieAdapter
    private lateinit var trendingMovieAdapter: TrendingMovieAdapter

    companion object {
        const val MOVIES = 1
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setup() {
        setupViewModel()
        getResponse()

        setupLatestMovieTransformer()
        setupSeriesMovieTransformer()

        runBlocking {
            launch {
                delay(3000)
                getMovies()
            }
        }
    }

    private fun setupViewModel() {
        movieViewModel =
            ViewModelProvider(this, MovieViewModelFactory())[MovieViewModel::class.java]
    }

    private fun getMovies() = movieViewModel.getMovies(MOVIES)

    private fun getResponse() {
        movieViewModel.response.observe(this) {
            when (it.code) {
                ResponseHelper.ERROR -> {
                    val error = it.response as String
                    toast(requireActivity(), error)
                }

                ResponseHelper.LOADING -> {
                    val loading = it.response as Boolean
                    if (!loading) loadingDone()
                }

                MOVIES -> {
                    val response = it.response as MovieModel
                    populateLatestMovie(response)
                    populateSeriesMovie(response)
                    populateTrendingMovie(response)
                }
            }
        }
    }

    private fun loadingDone() {
        binding.sflLatestMoviePlaceholder.visibility = View.GONE
        binding.sflSeriesMoviePlaceholder.visibility = View.GONE
        binding.sflTrendingEventPlaceholder.visibility = View.GONE
        binding.lLatestBanner.root.visibility = View.VISIBLE
        binding.lSeriesBanner.root.visibility = View.VISIBLE
        binding.rvTrendingMovie.visibility = View.VISIBLE
    }

    private fun populateLatestMovie(movieModel: MovieModel) {
        val bannerList = movieModel.results
            .mapNotNull { it.primaryImage?.url }
            .map { BannerModel.Data(bannerUrl = it) }

        latestMovieAdapter = LatestMovieAdapter(bannerList)
        binding.lLatestBanner.viewPagerBanner.adapter = latestMovieAdapter
        binding.lLatestBanner.viewPagerBanner.offscreenPageLimit = 3
        binding.lLatestBanner.viewPagerBanner.clipToPadding = false
        binding.lLatestBanner.viewPagerBanner.clipChildren = false
        binding.lLatestBanner.viewPagerBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun populateSeriesMovie(movieModel: MovieModel) {
        val bannerList = movieModel.results
            .mapNotNull { it.primaryImage?.url }
            .map { BannerModel.Data(bannerUrl = it) }

        seriesMovieAdapter = SeriesMovieAdapter(bannerList)
        binding.lSeriesBanner.viewPagerBanner.adapter = seriesMovieAdapter
        binding.lSeriesBanner.viewPagerBanner.offscreenPageLimit = 3
        binding.lSeriesBanner.viewPagerBanner.clipToPadding = false
        binding.lSeriesBanner.viewPagerBanner.clipChildren = false
        binding.lSeriesBanner.viewPagerBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun populateTrendingMovie(movieModel: MovieModel) {
        val bannerList = movieModel.results
            .mapNotNull { it.primaryImage?.url }
            .map { BannerModel.Data(bannerUrl = it) }

        trendingMovieAdapter = TrendingMovieAdapter(bannerList)
        binding.rvTrendingMovie.apply {
            this.adapter = trendingMovieAdapter
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setupLatestMovieTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r + 0.14f
        }
        binding.lLatestBanner.viewPagerBanner.setPageTransformer(transformer)
    }

    private fun setupSeriesMovieTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r + 0.14f
        }
        binding.lSeriesBanner.viewPagerBanner.setPageTransformer(transformer)
    }
}