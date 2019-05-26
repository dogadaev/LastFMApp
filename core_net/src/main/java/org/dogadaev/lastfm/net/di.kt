package org.dogadaev.lastfm.net

import okhttp3.OkHttpClient
import org.dogadaev.lastfm.net.api.API
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT_SEC = 30L

val HOST_TAG = StringQualifier("org.dogadaev.lastfm.net.HostTag")
val OKHTTP_TAG = StringQualifier("org.dogadaev.lastfm.net.OkhttpTag")
val API_KEY = StringQualifier("org.dogadaev.lastfm.net.ApiKeyTag")

val networkModule = module {
    single(HOST_TAG) { "https://ws.audioscrobbler.com/" }
    single(API_KEY) { "7667da5d5a6ed2ad147fcae32017dfde" }

    single(OKHTTP_TAG) {
        createOkhttpClient()
    }

    single { createRetrofitClient(get(HOST_TAG), get(OKHTTP_TAG)).create(API::class.java) }
}

private fun createOkhttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .build()

private fun createRetrofitClient(baseUrl: String, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()