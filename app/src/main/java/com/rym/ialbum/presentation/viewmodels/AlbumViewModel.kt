package com.rym.ialbum.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.domain.models.Resource
import com.rym.ialbum.domain.usecases.IAlbumsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlbumViewModel(private val albumUsecase: IAlbumsUseCase): ViewModel() {

    val _albums = MutableStateFlow<Resource<ArrayList<Album>>>(Resource.init())
    val abums : StateFlow<Resource<ArrayList<Album>>>
        get() = _albums
}