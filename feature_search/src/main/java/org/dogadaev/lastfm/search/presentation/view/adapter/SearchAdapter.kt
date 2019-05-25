package org.dogadaev.lastfm.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_artist.*
import org.dogadaev.lastfm.net.data.model.Artist
import org.dogadaev.lastfm.search.R
import org.dogadaev.lastfm.statical.media.ImageLoader
import org.koin.core.KoinComponent
import org.koin.core.get

class SearchAdapter : ListAdapter<Artist, SearchAdapter.ViewHolder>(SearchDiffCallback), KoinComponent {

    private val imageLoader: ImageLoader = get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(position: Int) {
            val context = root.context
            val artist = getItem(position)

            name.text = artist.name
            listeners.text = context.getString(R.string.listeners_count, artist.listeners)
            imageLoader.load {
                target(artistCover)
                src(artist.getImageUrl())
                noCaching()
                fallback(R.drawable.ic_no_image)
                error(R.drawable.ic_no_image)
            }

            root.setOnClickListener {

            }
        }
    }
}