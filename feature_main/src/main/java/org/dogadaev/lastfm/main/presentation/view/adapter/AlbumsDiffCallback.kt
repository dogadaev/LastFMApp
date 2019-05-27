package org.dogadaev.lastfm.main.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import org.dogadaev.lastfm.db.entity.AlbumDb

object AlbumsDiffCallback : DiffUtil.ItemCallback<AlbumDb>() {
    override fun areItemsTheSame(oldItem: AlbumDb, newItem: AlbumDb): Boolean =
        oldItem.artist == newItem.artist && oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: AlbumDb, newItem: AlbumDb): Boolean =
        oldItem == newItem
}