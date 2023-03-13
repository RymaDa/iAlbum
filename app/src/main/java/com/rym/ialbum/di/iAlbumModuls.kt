package com.rym.ialbum.di

import androidx.room.Room
import com.rym.ialbum.data.local.AlbumDAO
import com.rym.ialbum.data.local.AlbumDatabase
import com.rym.ialbum.data.repositories.AlbumRepository
import com.rym.ialbum.domain.irepositories.IAlbumRepository
import com.rym.ialbum.domain.usecases.*
import com.rym.ialbum.presentation.viewmodels.AlbumViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ioDispatcherModule = module {
    single { Dispatchers.IO }
}

val repositoruModule = module {
    single<IAlbumRepository> { AlbumRepository(get(), get()) }
}

val usecaselModule = module {
    single<IGetAlbumsUseCase> { GetAlbumsUseCase(get()) }
    single<IInsertAlbumsUseCase> { InsertAlbumsUseCase(get()) }
    single<IGetLocalAlbumsUseCase> { GetLocalAlbumsUseCase(get()) }
}

val viewmodelModule = module {
    viewModel { AlbumViewModel(get(), get(), get()) }

}

val localDbModule = module {
    single { AlbumDatabase(androidApplication()) }
    single { get<AlbumDatabase>().albumDao() }
}



