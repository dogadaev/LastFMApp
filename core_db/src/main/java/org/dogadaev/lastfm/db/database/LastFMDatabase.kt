package org.dogadaev.lastfm.db.database

import androidx.room.RoomDatabase
import org.dogadaev.lastfm.db.dao.AlbumsDao

//@Database(
//    entities = [],
//    version = 1
//)
abstract class ChapterDatabase : RoomDatabase() {
    abstract fun chapterDao(): AlbumsDao
}