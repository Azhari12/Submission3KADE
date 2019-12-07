package com.example.footballmatchschedule.api

import android.net.Uri
import com.example.footballmatchschedule.BuildConfig

object TheSportDBApi {


    fun getTeams(idLeague: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupleague.php")
            .appendQueryParameter("id", idLeague)
            .build()
            .toString()
    }
}