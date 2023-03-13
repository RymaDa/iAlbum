package com.rym.ialbum.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rym.ialbum.R
import com.rym.ialbum.data.models.Album
import com.rym.ialbum.presentation.viewmodels.AlbumViewModel

class AlbumAdapter(
    private val context: Context,
    val data : ArrayList<Album>,
    val listener: OnAlbumClickListener,
    private val viewModel: AlbumViewModel): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {

        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_album_item, parent, false)

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = data[position]
        holder.bind(album)
        holder.itemView.setOnClickListener { listener.onAlbumClick(album) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class AlbumViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val img: ImageView = itemView.findViewById(R.id.imageView)

        @SuppressLint("ResourceAsColor")
        fun bind(album: Album) {
            title.text = album.title
            img.setBackgroundColor(R.color.purple_200)
            Glide.with(context)
                .load(album.thumbnailUrl)
                .into(img)
        }
    }

    interface OnAlbumClickListener {
        fun onAlbumClick(album: Album)
    }


}