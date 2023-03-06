package com.rym.ialbum.data.remote

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.utils.Contants.Companion.ALBUM_END_POINT
import retrofit2.http.GET

interface AlbumApiService {
    @GET(ALBUM_END_POINT)
    suspend fun getAlbums(): ArrayList<Album>
}