package com.rym.ialbum.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rym.ialbum.R
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.presentation.viewmodels.AlbumViewModel

class AlbumAdapter( val listener: OnAlbumClickListener,
                   private val viewModel: AlbumViewModel): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    //private var albums: List<Album> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {

        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_album_item, parent, false)

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = viewModel.albums[position]
        holder.bind(album)
        holder.itemView.setOnClickListener { listener.onAlbumClick(album) }
    }

    override fun getItemCount(): Int {
        return viewModel.albums.size
    }

    inner class AlbumViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.album_title_tv)
        fun bind(album: Album) {
            title.text = album.title
            //binding.albumActor.text = album.actor
            // bind other album properties as needed
        }
    }

    interface OnAlbumClickListener {
        fun onAlbumClick(album: Album)
    }


}