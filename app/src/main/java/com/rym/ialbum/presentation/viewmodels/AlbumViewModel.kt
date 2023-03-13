package com.rym.ialbum.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.models.Resource
import com.rym.ialbum.domain.usecases.IGetAlbumsUseCase
import com.rym.ialbum.domain.usecases.IGetLocalAlbumsUseCase
import com.rym.ialbum.domain.usecases.IInsertAlbumsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumUsecase: IGetAlbumsUseCase,
                     private val insertAlbumUsecase: IInsertAlbumsUseCase,
                     private val getLocalAlbumsUsecase: IGetLocalAlbumsUseCase,
): ViewModel() {

    val _albums = MutableStateFlow<Resource<ArrayList<Album>>>(Resource.init())
    val albums : ArrayList<Album>
        get() = _albums.value.data ?: ArrayList()

    fun getRemoteAlbums(){
        viewModelScope.launch{
            albumUsecase.getRemoteAlbums().collect{
                _albums.value = it
            }
        }
    }
    fun insertAlbums(albums: ArrayList<Album>){
        viewModelScope.launch{
            insertAlbumUsecase.insertAlbums(albums)
        }
    }
    val _albumsl = MutableStateFlow<Resource<ArrayList<Album>>>(Resource.init())
    val albumsl : ArrayList<Album>
        get() = _albumsl.value.data ?: ArrayList()

    fun getLocalAlbums(){
        viewModelScope.launch{
            getLocalAlbumsUsecase.getLocalAlbums().collect{
                _albums.value = it
            }
        }
    }
}