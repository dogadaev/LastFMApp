package org.dogadaev.lastfm.albums.presentation.view.adapter

import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.albums.presentation.view.activity.AlbumsDiffCallback
import org.dogadaev.lastfm.net.data.model.albums.Album
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter

class AlbumsAdapter : SimpleListAdapter<Album>(AlbumsDiffCallback) {
    override val layoutRes: Int
        get() = R.layout.item_album
    override val bottomReachLimit: Int
        get() = 1

    override fun bind(position: Int, holder: ViewHolder) {
        val album = getItem(position)
    }
}