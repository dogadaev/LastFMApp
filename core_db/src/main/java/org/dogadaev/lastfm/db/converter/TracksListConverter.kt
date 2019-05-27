package org.dogadaev.lastfm.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.dogadaev.lastfm.db.entity.TrackDb

class TracksListConverter {
    @TypeConverter
    fun fromList(list: List<TrackDb>): String =
        Gson().toJson(list)

    @TypeConverter
    fun fromJson(json: String): List<TrackDb> {
        val listType = object : TypeToken<List<TrackDb>>() {}.type
        return Gson().fromJson(json, listType)
    }
}