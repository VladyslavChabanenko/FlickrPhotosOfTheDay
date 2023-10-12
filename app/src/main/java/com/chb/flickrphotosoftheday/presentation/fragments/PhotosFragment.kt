package com.chb.flickrphotosoftheday.presentation.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chb.flickrphotosoftheday.databinding.FragmentPhotosBinding
import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.presentation.adapters.recycler.PhotosAdapter
import com.chb.flickrphotosoftheday.presentation.base.BaseFragment
import com.chb.flickrphotosoftheday.presentation.viewmodels.PhotosUI
import com.chb.flickrphotosoftheday.presentation.viewmodels.PhotosViewModel
import com.chb.flickrphotosoftheday.presentation.viewmodels.PhotosViewModel.Companion.NUM_OF_COLUMNS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>() {

    private val viewModel: PhotosViewModel by viewModels()

    @Inject
    lateinit var photosAdapter: PhotosAdapter

    override fun getViewBinding(): FragmentPhotosBinding =
        FragmentPhotosBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        viewModel.getPhotos()
    }

    private fun initRecyclerView() {
        binding.photoList.apply {
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (viewTreeObserver.isAlive) viewTreeObserver.removeOnPreDrawListener(this)
                    adapter = photosAdapter
                        .apply {
                            itemWidth = width / 3F
                            itemHeight = height / 3F
                        }
                    return true
                }
            })
            layoutManager = GridLayoutManager(requireContext(), NUM_OF_COLUMNS)
        }

        photosAdapter.setItemClickListener { item ->
            PhotoInfoPagerFragment.navigate(findNavController(), item.id)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.photoList.collect { event ->
                when (event) {
                    is PhotosUI.Loading -> showProgressBar(true)
                    is PhotosUI.Success -> {
                        showProgressBar(false)
                        displayPhotos(event.data)
                    }
                    is PhotosUI.Error -> handleErrorMessage(event.error)
                }
            }
        }
    }

    private fun displayPhotos(photos: List<Photo>) {
        photosAdapter.list = photos
    }
}
