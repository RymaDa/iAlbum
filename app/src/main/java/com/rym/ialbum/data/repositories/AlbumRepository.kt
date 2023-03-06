package com.rym.ialbum.data.repositories

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.data.remote.AlbumApiService
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumRepository(private val albumApiService: AlbumApiService): IAlbumRepository {
    override suspend fun getAlbums(): Flow<Resource<ArrayList<Album>>> = flow{
        emit(Resource.loading())
        try {
            emit(Resource.success(albumApiService.getAlbums()))
        }catch (e: Exception){
            emit(Resource.error("Error"))
        }
    }
}