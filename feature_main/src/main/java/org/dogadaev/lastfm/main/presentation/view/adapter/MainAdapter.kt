package org.dogadaev.lastfm.main.presentation.view.adapter

import kotlinx.android.synthetic.main.item_saved_album.*
import org.dogadaev.lastfm.db.entity.AlbumDb
import org.dogadaev.lastfm.main.R
import org.dogadaev.lastfm.statical.format.formatBigNumber
import org.dogadaev.lastfm.statical.media.ImageLoader
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter
import org.koin.core.get

private typealias OnAlbumClickListener = (position: Int) -> Unit

class MainAdapter : SimpleListAdapter<AlbumDb>(AlbumsDiffCallback) {
    override val layoutRes: Int
        get() = R.layout.item_saved_album

    private val imageLoader: ImageLoader = get()

    private var onAlbumClickListener: OnAlbumClickListener? = null

    fun onAlbumClicked(listener: OnAlbumClickListener) {
        onAlbumClickListener = listener
    }

    override fun bind(position: Int, holder: ViewHolder) {
        val context = holder.root.context
        val album = getItem(position)
        val playCount = formatBigNumber(album.playCount)

        holder.name.text = album.name
        holder.playCount.text = context.getString(R.string.play_count, playCount)

        imageLoader.load {
            target(holder.albumCover)
            src(album.imageUrl)
            fallback(R.drawable.ic_no_image)
            error(R.drawable.ic_no_image)
            placeholder(R.drawable.ic_no_image)
        }

        holder.root.setOnClickListener {
            onAlbumClickListener?.invoke(position)
        }
    }
}