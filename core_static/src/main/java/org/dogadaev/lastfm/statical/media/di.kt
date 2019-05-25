package org.dogadaev.lastfm.statical.media

import com.bumptech.glide.Glide
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mediaModule = module {
    single { Glide.with(androidApplication()) }
    single<ImageLoader> { GlideImageLoader(get()) }
}