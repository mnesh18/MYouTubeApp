package com.example.myoutubeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutubeapp.databinding.ItemVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class VideoAdapter(val videosList: List<Video>,val  player: YouTubePlayer) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videosList[position]

        holder.binding.apply {
            btVideo.text = video.title
            btVideo.setOnClickListener {
                player.loadVideo(video.code,0F)
            }
        }
    }

    override fun getItemCount(): Int = videosList.size

}
