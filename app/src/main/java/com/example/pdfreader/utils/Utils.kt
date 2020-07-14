package com.example.pdfreader.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.pdfreader.R

object Utils {

    fun playPageSwipeSound(context: Context){
        val mediaPlayer = MediaPlayer.create(context, R.raw.page_swipe)
        mediaPlayer?.start()
    }

}