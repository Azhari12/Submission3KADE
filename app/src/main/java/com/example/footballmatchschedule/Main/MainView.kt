package com.example.footballmatchschedule.Main

import com.example.footballmatchschedule.Model.Item
import com.example.footballmatchschedule.Model.League

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<League>)
}