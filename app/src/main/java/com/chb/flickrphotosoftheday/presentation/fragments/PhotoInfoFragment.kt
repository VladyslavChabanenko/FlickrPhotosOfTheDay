package com.chb.flickrphotosoftheday.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.chb.flickrphotosoftheday.R
import com.chb.flickrphotosoftheday.databinding.FragmentPhotoInfoBinding
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import com.chb.flickrphotosoftheday.presentation.base.BaseFragment
import com.chb.flickrphotosoftheday.presentation.viewmodels.PhotoInfoViewModel
import com.chb.flickrphotosoftheday.utils.getSerializableCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotoInfoFragment : BaseFragment<FragmentPhotoInfoBinding>() {

    private val viewModel: PhotoInfoViewModel by viewModels({ requireParentFragment() })

    @Inject
    lateinit var glide: RequestManager

    override fun getViewBinding(): FragmentPhotoInfoBinding =
        FragmentPhotoInfoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGui()
    }

    private fun initGui() {
        val item: PhotoInfo = arguments?.getSerializableCompat(EXTRA_PHOTO) ?: viewModel.list.value.first()
        binding.apply {
            title.text = item.title
            description.text = item.description
            glide
                .load(item.url)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(image)
        }
    }

    companion object {
        private const val EXTRA_PHOTO = "EXTRA_PHOTO"

        fun getInstance(photo: PhotoInfo) =
            PhotoInfoFragment()
                .apply {
                    val bundle = bundleOf(
                        EXTRA_PHOTO to photo
                    )
                    this.arguments = bundle
                }
    }
}
