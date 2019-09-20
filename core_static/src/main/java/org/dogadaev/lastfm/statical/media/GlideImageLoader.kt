package org.dogadaev.lastfm.statical.media

import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GlideImageLoader(
    private val requestManager: RequestManager
) : ImageLoader {
    override suspend fun cache(uri: String?) {
        withContext(Dispatchers.IO) {
            val bitmap = loadImage(uri)
            println(bitmap)
        }
    }

    override fun load(builder: ImageLoader.RequestBuilder.() -> Unit) {
        val build = GlideBuilder()
        build.builder()

        val cacheStrategy = if (build.isCachingEnabled) DiskCacheStrategy.ALL else DiskCacheStrategy.NONE

        val requestBuilder: RequestBuilder<*> =
            requestManager.load(build.url).diskCacheStrategy(cacheStrategy)

        requestBuilder
            .apply(build.options)
            .into(build.target)
    }

    private fun loadImage(imageUrl: String?): Boolean {
        if (imageUrl == null || imageUrl.isEmpty()) return false

        return try {
            requestManager
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .preload()
            true
        } catch (e: Exception) {
            false
        } catch (e: OutOfMemoryError) {
            false
        }
    }

    private class GlideBuilder : ImageLoader.RequestBuilder {
        var options = RequestOptions()
        lateinit var target: ImageView
        var url: String? = null
        var isCachingEnabled: Boolean = true

        override fun target(target: ImageView) {
            this.target = target
        }

        override fun src(src: String?) {
            url = src
        }

        override fun placeholder(placeholder: Int) {
            options = options.placeholder(placeholder)
        }

        override fun fallback(placeholder: Int) {
            options = options.fallback(placeholder)
        }

        override fun error(placeholder: Int) {
            options = options.error(placeholder)
        }

        override fun noCache() {
            isCachingEnabled = false
        }
    }
}