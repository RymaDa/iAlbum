package com.rym.ialbum.domain.usecases

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


interface IGetLocalAlbumsUseCase {
    suspend fun getLocalAlbums(): Flow<Resource<ArrayList<Album>>>
}
class GetLocalAlbumsUseCase(private val albumRepository: IAlbumRepository): IGetLocalAlbumsUseCase{
    override suspend fun getLocalAlbums(): Flow<Resource<ArrayList<Album>>> {
        println("USE CASE -> getLocalAlbums ")
        return withContext(Dispatchers.IO) {
             albumRepository.getLocalAlbums()
        }
    }
}