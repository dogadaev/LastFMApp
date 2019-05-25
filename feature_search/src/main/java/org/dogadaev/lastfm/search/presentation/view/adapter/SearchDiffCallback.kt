package org.dogadaev.lastfm.search.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import org.dogadaev.lastfm.net.data.model.Artist

object SearchDiffCallback : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem == newItem
}