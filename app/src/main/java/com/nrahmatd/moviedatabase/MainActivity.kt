package com.nrahmatd.moviedatabase

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.nrahmatd.moviedatabase.baseview.BaseActivity
import com.nrahmatd.moviedatabase.baseview.BaseViewPager2Adapter
import com.nrahmatd.moviedatabase.databinding.ActivityMainBinding
import com.nrahmatd.moviedatabase.view.CategoriesFragment
import com.nrahmatd.moviedatabase.view.DownloadFragment
import com.nrahmatd.moviedatabase.view.HomeFragment
import com.nrahmatd.moviedatabase.view.MoreFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback
    private var tabSelected: Int = R.id.nav_home_page

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        setFragment()
    }

    override fun statusBarColor(): Int = 0

    private fun setFragment() {
        val homeBinding = HomeFragment()
        val categoriesBinding = CategoriesFragment()
        val downloadFragment = DownloadFragment()
        val moreBinding = MoreFragment()

        val fragment = ArrayList<Fragment>()
        fragment.add(homeBinding)
        fragment.add(categoriesBinding)
        fragment.add(downloadFragment)
        fragment.add(moreBinding)

        binding.viewPager.adapter = BaseViewPager2Adapter(this, fragment)
        binding.viewPager.offscreenPageLimit = fragment.size
        binding.viewPager.isUserInputEnabled = false

        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setActive(position)
                super.onPageSelected(position)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)
        binding.bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home_page -> binding.viewPager.currentItem = 0
                R.id.nav_categories_page -> binding.viewPager.currentItem = 1
                R.id.nav_download_page -> binding.viewPager.currentItem = 2
                R.id.nav_more_page -> binding.viewPager.currentItem = 3
            }
            true
        }

        binding.bottomNavView.selectedItemId = tabSelected
    }

    private fun setActive(pos: Int) {
        when (pos) {
            0 -> binding.bottomNavView.selectedItemId = R.id.nav_home_page
            1 -> binding.bottomNavView.selectedItemId = R.id.nav_categories_page
            2 -> binding.bottomNavView.selectedItemId = R.id.nav_download_page
            3 -> binding.bottomNavView.selectedItemId = R.id.nav_more_page
        }
    }
}