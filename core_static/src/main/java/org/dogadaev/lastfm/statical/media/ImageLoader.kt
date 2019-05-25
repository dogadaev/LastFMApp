package org.dogadaev.lastfm.statical.media

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {
    fun load(builder: RequestBuilder.() -> Unit)

    interface RequestBuilder {
        fun target(target: ImageView)
        fun src(src: String?)
        fun placeholder(@DrawableRes placeholder: Int)
        fun fallback(@DrawableRes placeholder: Int)
        fun error(@DrawableRes placeholder: Int)
        fun noCaching()
    }
}