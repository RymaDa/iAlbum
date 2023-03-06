package com.rym.ialbum.di

import com.rym.ialbum.data.repositories.AlbumRepository
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.usecases.AlbumsUseCase
import com.rym.ialbum.domain.usecases.IAlbumsUseCase
import com.rym.ialbum.presentation.viewmodels.AlbumViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ioDispatcherModule = module {
    single { Dispatchers.IO }
}

val repositoruModule = module {
    single<IAlbumRepository> { AlbumRepository(get()) }
}

val usecaselModule = module {
    single<IAlbumsUseCase> { AlbumsUseCase(get()) }
}

val viewmodelModule = module {
    viewModel { AlbumViewModel(get()) }

}




