package com.icaarusdev.ezymoviefinder.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getImageProgress(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply{
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.setImage(uri: String, progressDrawable: CircularProgressDrawable){

}