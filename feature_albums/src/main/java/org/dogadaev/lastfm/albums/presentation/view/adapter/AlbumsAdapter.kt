package org.dogadaev.lastfm.albums.presentation.view.adapter

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.item_album.*
import org.dogadaev.lastfm.albums.R
import org.dogadaev.lastfm.albums.data.model.AlbumCommon
import org.dogadaev.lastfm.statical.media.ImageLoader
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter
import org.koin.core.get

private typealias OnAlbumClickListener = (position: Int) -> Unit
private typealias OnSaveDeleteClickListener = (position: Int) -> Unit

class AlbumsAdapter : SimpleListAdapter<AlbumCommon>(AlbumsDiffCallback) {
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

    fun onSaveDeleteClicked(listener: OnSaveDeleteClickListener) {
        onSaveDeleteClickListener = listener
    }

    override fun bind(position: Int, holder: ViewHolder) {
        val context = holder.root.context
        val album = getItem(position)

        holder.name.text = album.name
        holder.playCount.text = context.getString(R.string.play_count, album.playCount)

        imageLoader.load {
            target(holder.albumCover)
            src(album.imageUrl)
            noCaching()
            fallback(R.drawable.ic_no_image)
            error(R.drawable.ic_no_image)
            placeholder(R.drawable.ic_no_image)
        }

        holder.containerView.setOnClickListener {
            onAlbumClickListener?.invoke(position)
        }

        holder.progress.isVisible = album.isBeingProcessed
        holder.saveDeleteButton.isInvisible = album.isBeingProcessed
        holder.saveDeleteButton.isActivated = album.isStored

        holder.saveDeleteButton.setOnClickListener {
            album.isBeingProcessed = true
            holder.progress.isVisible = true
            holder.saveDeleteButton.isInvisible = true
            onSaveDeleteClickListener?.invoke(position)
        }
    }
}