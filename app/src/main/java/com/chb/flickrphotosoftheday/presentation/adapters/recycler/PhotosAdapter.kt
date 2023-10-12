package com.chb.flickrphotosoftheday.presentation.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.chb.flickrphotosoftheday.R
import com.chb.flickrphotosoftheday.databinding.ItemPhotoListBinding
import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.presentation.base.BaseRecyclerViewAdapter
import javax.inject.Inject

class PhotosAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseRecyclerViewAdapter<Photo>() {

    var itemWidth: Float? = null
    var itemHeight: Float? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Photo> {
        override fun bind(item: Photo) {
            binding.apply {
                itemWidth?.let { image.layoutParams.width = it.toInt() }
                itemHeight?.let { image.layoutParams.height = it.toInt() }
                title.text = item.title
                glide
                    .load(item.previewUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(image)
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}
