package com.rym.ialbum.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rym.ialbum.data.models.Album

@Dao
interface AlbumDAO {

    @Query("SELECT * FROM albums")
    fun getLocalAlbums(): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserAlbum(album: Album)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<Album>)

    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getAlbumById(albumId: Int): Album?
}