package com.rym.ialbum.domain.irepositories

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface IAlbumRepository {
    suspend fun getRemoteAlbums(): Flow<Resource<ArrayList<Album>>>
    suspend fun getLocalAlbums(): Flow<Resource<ArrayList<Album>>>
    suspend fun insertAlbums(albums: ArrayList<Album>)
}