package org.dogadaev.lastfm.statical.format

fun formatBigNumber(number: Int): String =
    String.format("%,d", number).replace(',', ' ')