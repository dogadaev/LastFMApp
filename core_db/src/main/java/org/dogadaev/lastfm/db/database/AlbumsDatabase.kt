package org.dogadaev.lastfm.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.dogadaev.lastfm.db.converter.TracksListConverter
import org.dogadaev.lastfm.db.dao.AlbumsDao
import org.dogadaev.lastfm.db.entity.AlbumDb

@Database(
    entities = [AlbumDb::class],
    version = 1
)
@TypeConverters(TracksListConverter::class)
abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao
}