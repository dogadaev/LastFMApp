package org.dogadaev.lastfm.net.data.repository

import android.content.Context
import android.net.ConnectivityManager

class NetworkCheckerImpl(private val context: Context): NetworkChecker {
    override val isNetworkConnected: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
}