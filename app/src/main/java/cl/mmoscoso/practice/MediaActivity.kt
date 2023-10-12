package cl.mmoscoso.practice

import android.media.MediaPlayer
import android.media.PlaybackParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import java.util.Locale

class MediaActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var tvCurrentTime: TextView
    private var playbackParams: PlaybackParams? = null
    private var isSeeking = false
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        mediaPlayer = MediaPlayer.create(this, R.raw.kickstarter)
        playbackParams = mediaPlayer.playbackParams
        // Initialize UI elements
        seekBar = findViewById(R.id.seekBar)
        tvCurrentTime = findViewById(R.id.tvCurrentTime)


        // Set up the SeekBar
        seekBar.max = mediaPlayer.duration
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeeking = false
            }
        })


        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                updateSeekBar()
            }
        }

        val stopButton = findViewById<Button>(R.id.stopButton)
        stopButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
                updateSeekBar()
            }
        }

        val pauseButton = findViewById<Button>(R.id.pauseButton)
        pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                updateSeekBar()
            }
        }

        val loopButton = findViewById<Button>(R.id.loopButton)
        loopButton.setOnClickListener {
            mediaPlayer.isLooping = !mediaPlayer.isLooping
        }

        val speedUpButton = findViewById<Button>(R.id.speedUpButton)
        speedUpButton.setOnClickListener {
            //mediaPlayer.playbackParams.speed += 0.5f
            changePlaybackSpeed(0.5f)

        }

        val speedDownButton = findViewById<Button>(R.id.speedDownButton)
        speedDownButton.setOnClickListener {
            //mediaPlayer.playbackParams.speed -= 0.5f
            changePlaybackSpeed(-0.5f)
        }
    }

    private fun changePlaybackSpeed(speedChange: Float) {
        playbackParams?.setSpeed(playbackParams!!.speed + speedChange)
        mediaPlayer.playbackParams = playbackParams!!
    }

    private fun resetPlaybackSpeed() {
        playbackParams?.setSpeed(1.0f)
        mediaPlayer.playbackParams = playbackParams!!
    }

    override fun onPause() {
        super.onPause()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            updateSeekBar()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            updateSeekBar()
        }
    }

    private fun updateSeekBar() {
        if (!isSeeking) {
            seekBar.progress = mediaPlayer.currentPosition
            val minutes = mediaPlayer.currentPosition / 1000 / 60
            val seconds = mediaPlayer.currentPosition / 1000 % 60
            val formattedTime = String.format(Locale.getDefault(), "%d:%02d", minutes, seconds)
            tvCurrentTime.text = formattedTime
        }
        if (mediaPlayer.isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}