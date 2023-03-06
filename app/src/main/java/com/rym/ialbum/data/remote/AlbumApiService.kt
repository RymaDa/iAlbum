package com.rym.ialbum.data.remote

import com.rym.ialbum.data.models.Album
import retrofit2.http.GET

interface AlbumApiService {
    @GET
    suspend fun getAlbums(): ArrayList<Album>
}