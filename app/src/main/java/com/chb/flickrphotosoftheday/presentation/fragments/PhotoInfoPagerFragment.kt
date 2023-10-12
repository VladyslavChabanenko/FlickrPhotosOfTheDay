package com.chb.flickrphotosoftheday.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.chb.flickrphotosoftheday.R
import com.chb.flickrphotosoftheday.databinding.FragmentPhotoInfoPagerBinding
import com.chb.flickrphotosoftheday.presentation.adapters.viewpager.PhotoInfoAdapter
import com.chb.flickrphotosoftheday.presentation.base.BaseFragment
import com.chb.flickrphotosoftheday.presentation.viewmodels.PhotoInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoInfoPagerFragment : BaseFragment<FragmentPhotoInfoPagerBinding>() {

    private val viewModel: PhotoInfoViewModel by viewModels()

    private lateinit var photoInfoAdapter: PhotoInfoAdapter

    override fun getViewBinding(): FragmentPhotoInfoPagerBinding =
        FragmentPhotoInfoPagerBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initObservers()
        viewModel.getPhotoInfoList()
    }

    private fun initObservers() {
        val id = arguments?.getLong(EXTRA_PHOTO_ID)
        lifecycleScope.launch {
            viewModel.list.collect { list ->
                photoInfoAdapter.setItems(list)
                val position = list.indexOfFirst { it.id == id }
                binding.photoDetailsPager.setCurrentItem(position, false)
            }
        }
    }

    private fun initViewPager() {
        photoInfoAdapter = PhotoInfoAdapter(this)
        binding.apply {
            photoDetailsPager.adapter = photoInfoAdapter
        }
    }

    companion object {
        private const val EXTRA_PHOTO_ID = "EXTRA_PHOTO_ID"

        fun navigate(
            navController: NavController,
            photoId: Long
        ) {
            navController.navigate(
                R.id.action_photosFragment_to_photoInfoPagerFragment, bundleOf(
                    EXTRA_PHOTO_ID to photoId
                )
            )
        }
    }
}
