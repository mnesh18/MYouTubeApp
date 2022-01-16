package com.example.myoutubeapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var videosList: List<Video>
    lateinit var gridView: GridView

    lateinit var playerView: YouTubePlayerView
    lateinit var player: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternet()
        videosList = listOf(
            Video("Mayada Bseliss - AR", "WsYc4eNGnzg"),
            Video("Mohamed AdElWahab - AR", "JPGHCLQQgp8"),
            Video("Gloria Gaynor - EN", "gYkACVDFmeg"),
            Video("Frank Sinatra - EN", "jx0llzSntd8"),
        )
        gridView = findViewById(R.id.gridView)
        playerView = findViewById(R.id.player)

        playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo("L8KgdWNSGtc", 0F)
                initRV()
            }
        })
    }


    private fun initRV() {
        gridView.adapter = GridVideosAdapter(videosList, player, this)
    }

    private fun checkInternet() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork?.isConnectedOrConnecting == false) {
            AlertDialog.Builder(this).setTitle("No Internet")
                .setMessage("Please Connect to a Network")
                .setPositiveButton("OK") { _, _ -> checkInternet() }.show()
        }

    }
}