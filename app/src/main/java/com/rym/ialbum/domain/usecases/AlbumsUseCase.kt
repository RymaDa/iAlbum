package com.rym.ialbum.domain.usecases

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface IAlbumsUseCase {
    suspend fun getAlbums(): Flow<Resource<ArrayList<Album>>>
}
class AlbumsUseCase(private val albumRepository: IAlbumRepository): IAlbumsUseCase{
    override suspend fun getAlbums(): Flow<Resource<ArrayList<Album>>> {
        return albumRepository.getAlbums()
    }

}