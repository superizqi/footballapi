package com.dicoding.kotlinacademy.api

import android.net.Uri
import com.dicoding.kotlinacademy.BuildConfig

object TheSportDBApi {

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", "4328")
                .build()
                .toString()
    }
}