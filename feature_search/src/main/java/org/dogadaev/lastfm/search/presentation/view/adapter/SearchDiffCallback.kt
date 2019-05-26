package org.dogadaev.lastfm.search.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import org.dogadaev.lastfm.net.data.model.search.SearchArtist

object SearchDiffCallback : DiffUtil.ItemCallback<SearchArtist>() {
    override fun areItemsTheSame(oldItem: SearchArtist, newItem: SearchArtist): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: SearchArtist, newItem: SearchArtist): Boolean =
        oldItem == newItem
}