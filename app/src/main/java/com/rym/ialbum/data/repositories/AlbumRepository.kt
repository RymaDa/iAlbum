package com.rym.ialbum.data.repositories

import com.rym.ialbum.data.local.AlbumDAO
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.data.remote.AlbumApiService
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class AlbumRepository(private val albumApiService: AlbumApiService,
                      private val albumDAO: AlbumDAO): IAlbumRepository {

    override suspend fun getRemoteAlbums(): Flow<Resource<ArrayList<Album>>> = flow{
        emit(Resource.loading())
        try {
            emit(Resource.success(albumApiService.getAlbums()))
        }catch (e: Exception){
            emit(Resource.error("Error"))
        }
    }

    override suspend fun getLocalAlbums(): Flow<Resource<ArrayList<Album>>> =flow{
            emit(Resource.loading())
            try {
                emit(Resource.success(ArrayList(albumDAO.getLocalAlbums())))
            } catch (e: Exception) {
                emit(Resource.error("Error ${e.message}"))
            }

    }.flowOn(Dispatchers.IO)

    override suspend fun insertAlbums(albums: ArrayList<Album>){
        try {
            println("insert repo")
            albumDAO.insertAll(albums.toList())
        }catch (e: Exception){
            println("insert repo err ${e.message}")
        }
    }
}