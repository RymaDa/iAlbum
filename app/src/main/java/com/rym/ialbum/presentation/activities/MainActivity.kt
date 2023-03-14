package com.rym.ialbum.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
import com.rym.ialbum.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val vm : AlbumViewModel
        get() = getViewModel()

    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setypCollectedData()
        setupView()
        fetchData()


    }

    private fun setupView() {
        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }
        setupSearchBar()
    }

    private fun setupSearchBar() {
        search_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                println("This filter"+s)
                val list  = (vm.albums as? ArrayList<Album>) ?: ArrayList()
                val listFiltred = list.filter { it.albumId.toString().toUpperCase().contains(s.toString().toUpperCase())
                        || it.title.toString().toUpperCase().contains(s.toString().toUpperCase()) }

                setupRecyclerViewData(ArrayList(listFiltred))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun fetchData() {
        if (NetworkUtils.isNetworkConnected(this))
            vm.getRemoteAlbums()
        else
            vm.getLocalAlbums()

    }

    private fun setypCollectedData() {
        lifecycleScope.launch {
            vm._albums.collectLatest {
                println("Albums data --> ${Gson().toJson(it)}")
                println("Albums data --> ${Gson().toJson(it.data?.size)}")
                when(it.status){
                    Resource.Status.LOADING -> {
                        swipeRefreshLayout.isRefreshing = true
                    }
                    Resource.Status.ERROR -> {
                        swipeRefreshLayout.isRefreshing = false
                    }
                    Resource.Status.SUCCESS -> {
                        setupRecyclerViewData(vm.albums)
                    }
                    else ->{
                        swipeRefreshLayout.isRefreshing = false
                    }
                }
            }

        }
    }
    private fun setupRecyclerViewData(data : ArrayList<Album>) {
        if (data.size>0){
            vm.insertAlbums(vm.albums)
           initRecyclerView(data)
            swipeRefreshLayout.isRefreshing = false
        }else{
            album_data_state.visibility = View.VISIBLE
            album_data_state.setText(R.string.empty_data)
            album_recycler_view.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun initRecyclerView(data: ArrayList<Album>) {
        album_data_state.visibility = View.GONE
        album_recycler_view.visibility = View.VISIBLE
        albumAdapter = AlbumAdapter(this, data,object : AlbumAdapter.OnAlbumClickListener {
            override fun onAlbumClick(album: Album) {
            }
        }, vm)
        album_recycler_view.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}