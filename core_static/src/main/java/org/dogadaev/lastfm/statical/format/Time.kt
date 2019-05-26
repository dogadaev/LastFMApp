package org.dogadaev.lastfm.statical.format

fun formatDurationTime(timeSec: Long): String {
    val minutes: Long = timeSec / 60
    val seconds = timeSec - minutes * 60
    return String.format("%d:%02d", minutes, seconds)
}