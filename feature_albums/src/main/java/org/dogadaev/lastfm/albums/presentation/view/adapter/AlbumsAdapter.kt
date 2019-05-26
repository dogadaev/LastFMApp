package org.dogadaev.lastfm.albums.presentation.view.adapter

import kotlinx.android.synthetic.main.item_album.*
import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.net.data.model.albums.Album
import org.dogadaev.lastfm.net.data.model.getImageUrl
import org.dogadaev.lastfm.statical.media.ImageLoader
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter
import org.koin.core.get

private typealias OnAlbumClickListener = (position: Int) -> Unit
private typealias OnSaveDeleteClickListener = (position: Int) -> Unit

class AlbumsAdapter : SimpleListAdapter<Album>(AlbumsDiffCallback) {
    override val layoutRes: Int
        get() = R.layout.item_album

    override val bottomReachLimit: Int
        get() = 1

    private val imageLoader: ImageLoader = get()
    private var onAlbumClickListener: OnAlbumClickListener? = null
    private var onSaveDeleteClickListener: OnSaveDeleteClickListener? = null

    fun onAlbumClicked(listener: OnAlbumClickListener) {
        onAlbumClickListener = listener
    }

    override fun bind(position: Int, holder: ViewHolder) {
        val context = holder.root.context
        val album = getItem(position)

        holder.name.text = album.name
        holder.playCount.text = context.getString(R.string.play_count, album.playCount)

        imageLoader.load {
            target(holder.albumCover)
            src(album.images.getImageUrl())
            noCaching()
            fallback(R.drawable.ic_no_image)
            error(R.drawable.ic_no_image)
        }

        holder.containerView.setOnClickListener {
            onAlbumClickListener?.invoke(position)
        }

        holder.saveDeleteButton.setOnClickListener {
            // todo: implement saving/deletion of album
        }
    }
}