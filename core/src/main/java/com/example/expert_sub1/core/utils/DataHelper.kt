package com.example.expert_sub1.core.utils

import com.example.expert_sub1.core.BuildConfig

object DataHelper {
    fun rawImagePath(subPath: String) = BuildConfig.IMG_URL + subPath

    fun displayType(type: String) = when(type){
        "movie" -> "Movie"
        "tv" -> "Tv Show"
        else -> "-"
    }

    fun dateToString(date: String): String {
        val month = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Des")
        val spl = date.split("-")
        val y = spl[0]
        val m = spl[1].toInt()
        val d = spl[2].toInt()
        return "${month[m-1]} $d, $y"
    }
}