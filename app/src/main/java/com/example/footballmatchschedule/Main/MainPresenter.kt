package com.example.footballmatchschedule.Main

import android.util.Log
import com.example.footballmatchschedule.Model.League
import com.example.footballmatchschedule.Model.TeamResponse
import com.example.footballmatchschedule.api.ApiRepository
import com.example.footballmatchschedule.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private var leagues: MutableList<League> = mutableListOf()

) {
    fun getLeagueDetail(league: String?) {
        //view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams("4328")),
                TeamResponse::class.java
            )

            leagues.addAll(data.leagues)
            Log.d("dataleague1", leagues.size.toString())
            /*uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }*/
        }
    }
}