package com.rym.ialbum.di

import com.rym.ialbum.data.remote.AlbumApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val interceptor = HttpLoggingInterceptor() //
    interceptor.level = HttpLoggingInterceptor.Level.BASIC

    // http client
    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    // retrofit
    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //services
    single { get<Retrofit>().create(AlbumApiService::class.java) }

}