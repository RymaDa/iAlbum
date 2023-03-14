package com.rym.ialbum

import androidx.test.runner.AndroidJUnit4
import com.rym.ialbum.data.remote.AlbumApiService
import com.rym.ialbum.utils.Contants.Companion.BASE_URL
import junit.framework.TestCase
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class RemoteDataTest {

    private lateinit var service: AlbumApiService

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(AlbumApiService::class.java)
    }

    @Test
     fun testGetAlbums() {
        runBlocking {
            val albums = service.getAlbums()
            assertTrue(albums.isNotEmpty())
            assertNotNull(albums)
            TestCase.assertEquals(5000, albums.size)

        }

    }

}