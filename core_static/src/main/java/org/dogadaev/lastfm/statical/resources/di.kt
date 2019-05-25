package org.dogadaev.lastfm.statical.resources

import org.koin.dsl.module

val resourceProviderModule = module {
    single<ResourceProvider> { AndroidResourceProvider(get()) }
}