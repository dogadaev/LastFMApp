package org.dogadaev.lastfm.details.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import org.dogadaev.lastfm.net.data.model.details.TrackInfo

object DetailsDiffCallback : DiffUtil.ItemCallback<TrackInfo>() {
    override fun areItemsTheSame(oldItem: TrackInfo, newItem: TrackInfo): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: TrackInfo, newItem: TrackInfo): Boolean =
        oldItem == newItem
}