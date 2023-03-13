package com.rym.ialbum.domain.usecases

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface IGetAlbumsUseCase {
    suspend fun getRemoteAlbums(): Flow<Resource<ArrayList<Album>>>
}
class GetAlbumsUseCase(private val albumRepository: IAlbumRepository): IGetAlbumsUseCase{
    override suspend fun getRemoteAlbums(): Flow<Resource<ArrayList<Album>>> {
        return albumRepository.getRemoteAlbums()
    }
}