package com.rym.ialbum.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rym.ialbum.R
import com.rym.ialbum.presentation.viewmodels.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import com.google.gson.Gson
import com.rym.ialbum.domain.models.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.presentation.adapters.AlbumAdapter


class MainActivity : AppCompatActivity() {

    private val vm : AlbumViewModel
        get() = getViewModel()

    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setypCollectedData()
        fetchData()
    }

    private fun fetchData() {
        vm.getRemoteAlbums()
    }

    private fun setypCollectedData() {
        lifecycleScope.launch {
            vm._albums.collectLatest {
                println("Albums data --> ${Gson().toJson(it)}")
                when(it.status){
                    Resource.Status.LOADING -> {}
                    Resource.Status.ERROR -> {}
                    Resource.Status.SUCCESS -> {
                        setupRecyclerViewData()
                    }
                    else ->{}
                }
            }

        }
    }

    private fun setupRecyclerViewData() {
        val rvAlbums: RecyclerView = findViewById(R.id.album_recycler_view)

        // création de l'adapter
        albumAdapter = AlbumAdapter(object : AlbumAdapter.OnAlbumClickListener {
            override fun onAlbumClick(album: Album) {
                // traiter l'événement de clic sur un album
            }
        }, vm)

        // initialisation du RecyclerView avec l'adapter
        rvAlbums.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}