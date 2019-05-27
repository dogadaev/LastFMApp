package org.dogadaev.lastfm.details.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import org.dogadaev.lastfm.details.data.model.TrackCommon

object DetailsDiffCallback : DiffUtil.ItemCallback<TrackCommon>() {
    override fun areItemsTheSame(oldItem: TrackCommon, newItem: TrackCommon): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: TrackCommon, newItem: TrackCommon): Boolean =
        oldItem == newItem
}