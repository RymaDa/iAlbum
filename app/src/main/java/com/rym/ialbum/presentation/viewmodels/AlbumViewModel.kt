package com.rym.ialbum.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.models.Resource
import com.rym.ialbum.domain.usecases.IAlbumsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumUsecase: IAlbumsUseCase): ViewModel() {

    val _albums = MutableStateFlow<Resource<ArrayList<Album>>>(Resource.init())
    val albums : ArrayList<Album>
        get() = _albums.value.data ?: ArrayList()

    fun getRemoteAlbums(){
        viewModelScope.launch{
            albumUsecase.getAlbums().collect{
                _albums.value = it
            }
        }
    }
}