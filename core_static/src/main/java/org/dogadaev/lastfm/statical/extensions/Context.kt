package org.dogadaev.lastfm.statical.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(@ColorRes color: Int) =
    ContextCompat.getColor(this, color)