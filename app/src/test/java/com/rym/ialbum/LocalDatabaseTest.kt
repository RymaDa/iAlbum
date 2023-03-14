package com.rym.ialbum


import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.rym.ialbum.data.local.AlbumDAO
import com.rym.ialbum.data.local.AlbumDatabase
import com.rym.ialbum.data.models.Album
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDatabaseTest {
    private lateinit var dbase: AlbumDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        dbase = Room.inMemoryDatabaseBuilder(
             ApplicationProvider.getApplicationContext(), AlbumDatabase::class.java)
             .allowMainThreadQueries()
             .build()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        dbase.close()
    }

    @Test
    fun testGetLocalAlbums() {
        runBlocking {

            val album1 = Album(1,2,  "Album 1", "https://example.com/album1.jpg", "https://example.com/album1.jpg")
            val album2 = Album(2,2, "Album 2", "https://example.com/album2.jpg", "https://example.com/album2.jpg")
            val album3 = Album(3,3, "Album 3", "https://example.com/album3.jpg", "https://example.com/album3.jpg")

            dbase.albumDao().insertAll(listOf(album1, album2, album3))

            val localAlbums = dbase.albumDao().getLocalAlbums()

            assertEquals(3, localAlbums.size)
            assertTrue(localAlbums.contains(album1))
            assertTrue(localAlbums.contains(album2))
            assertTrue(localAlbums.contains(album3))
        }

    }
}