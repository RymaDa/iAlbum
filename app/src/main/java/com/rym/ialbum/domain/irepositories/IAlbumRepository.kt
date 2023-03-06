package com.rym.ialbum.domain.irepositories

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface IAlbumRepository {
    suspend fun getAlbums(): Flow<Resource<ArrayList<Album>>>
}