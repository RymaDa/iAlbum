package com.rym.ialbum.domain.usecases

import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface IInsertAlbumsUseCase {
    suspend fun insertAlbums(albums: ArrayList<Album>)
}
class InsertAlbumsUseCase(private val albumRepository: IAlbumRepository): IInsertAlbumsUseCase{
    override suspend fun insertAlbums(albums: ArrayList<Album>) {

        withContext(Dispatchers.IO) {
            albumRepository.insertAlbums(albums)
        }
    }


}