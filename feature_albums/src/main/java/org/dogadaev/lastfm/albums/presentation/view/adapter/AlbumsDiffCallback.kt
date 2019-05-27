package org.dogadaev.lastfm.albums.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import org.dogadaev.lastfm.albums.data.model.AlbumCommon

object AlbumsDiffCallback : DiffUtil.ItemCallback<AlbumCommon>() {
    override fun areItemsTheSame(oldItem: AlbumCommon, newItem: AlbumCommon): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: AlbumCommon, newItem: AlbumCommon): Boolean =
        oldItem == newItem
}