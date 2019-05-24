package org.dogadaev.statical.resources

import org.koin.dsl.module

val resourceProviderModule = module {
    single<ResourceProvider> { AndroidResourceProvider(get()) }
}