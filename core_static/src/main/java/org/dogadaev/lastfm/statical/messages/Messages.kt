package org.dogadaev.lastfm.statical.messages

import android.content.Context
import android.widget.Toast

fun showInfoMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        context,
        message,
        duration
    ).show()
}