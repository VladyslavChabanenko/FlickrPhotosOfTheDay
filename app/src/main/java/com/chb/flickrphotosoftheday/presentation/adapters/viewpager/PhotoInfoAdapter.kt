package com.chb.flickrphotosoftheday.presentation.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import com.chb.flickrphotosoftheday.presentation.fragments.PhotoInfoFragment
import com.chb.flickrphotosoftheday.presentation.fragments.PhotoInfoPagerFragment

class PhotoInfoAdapter(
    fragment: PhotoInfoPagerFragment
) : FragmentStateAdapter(fragment) {

    private var photos: List<PhotoInfo> = arrayListOf()

    fun setItems(photos: List<PhotoInfo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    override fun getItemCount() = photos.size

    override fun createFragment(position: Int): Fragment =
        PhotoInfoFragment.getInstance(photos[position])

}
