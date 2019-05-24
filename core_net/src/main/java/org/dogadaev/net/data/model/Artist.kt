package org.dogadaev.net.data.model

data class Artist(
    val name: String = "",
    val listeners: Int = 0,
    val url: String = "",
    val mbid: String = "",
    val streamable: Boolean = false,
    val image: HashMap<String, String> = hashMapOf()
)