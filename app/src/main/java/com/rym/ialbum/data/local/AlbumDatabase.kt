package com.rym.ialbum.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rym.ialbum.data.models.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDAO

    companion object {
        @Volatile
        private var instance: AlbumDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AlbumDatabase::class.java, "AlbumDatabase.db")
                //.createFromAsset("database/ialbumapp.db")
                .build()
    }

}