package org.dogadaev.lastfm.details.presentation.view.adapter

import kotlinx.android.synthetic.main.item_track.*
import org.dogadaev.lastfm.details.R
import org.dogadaev.lastfm.details.data.model.TrackCommon
import org.dogadaev.lastfm.statical.format.formatDurationTime
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter

class DetailsAdapter : SimpleListAdapter<TrackCommon>(DetailsDiffCallback) {
    override val layoutRes: Int
        get() = R.layout.item_track

    override fun bind(position: Int, holder: ViewHolder) {
        val track = getItem(position)
        val duration = formatDurationTime(track.duration)

        holder.title.text = track.name
        holder.duration.text = duration

        holder.root.setOnClickListener {
            // no op
        }
    }
}