package org.dogadaev.lastfm.search.presentation.view.adapter

import kotlinx.android.synthetic.main.item_artist.*
import org.dogadaev.lastfm.net.data.model.getImageUrl
import org.dogadaev.lastfm.net.data.model.search.SearchArtist
import org.dogadaev.lastfm.search.R
import org.dogadaev.lastfm.statical.media.ImageLoader
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter
import org.koin.core.get

class SearchAdapter : SimpleListAdapter<SearchArtist>(SearchDiffCallback) {
    override val layoutRes: Int
        get() = R.layout.item_artist

    override val bottomReachLimit: Int
        get() = 5

    private val imageLoader: ImageLoader = get()

    override fun bind(position: Int, holder: ViewHolder) {
        val context = holder.root.context
        val artist = getItem(position)

        holder.name.text = artist.name
        holder.listeners.text = context.getString(R.string.listeners_count, artist.listeners)

        imageLoader.load {
            target(holder.artistCover)
            src(artist.images.getImageUrl())
            noCaching()
            fallback(R.drawable.ic_no_image)
            error(R.drawable.ic_no_image)
        }

        holder.root.setOnClickListener {
            // todo: implement AlbumsScreen open
        }
    }
}