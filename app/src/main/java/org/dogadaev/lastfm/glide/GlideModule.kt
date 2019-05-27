package org.dogadaev.lastfm.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.Call
import okhttp3.OkHttpClient
import org.dogadaev.lastfm.net.OKHTTP_TAG
import org.dogadaev.lastfm.statical.media.MediaContentTag
import org.koin.core.KoinComponent
import org.koin.core.get
import java.io.InputStream

@GlideModule
class GlideModule : AppGlideModule(), KoinComponent {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client: OkHttpClient = get(OKHTTP_TAG)
        val factory = Call.Factory {
            val request = it.newBuilder().tag(MediaContentTag::class.java, MediaContentTag).build()
            client.newCall(request)
        }
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(factory))
    }
}
