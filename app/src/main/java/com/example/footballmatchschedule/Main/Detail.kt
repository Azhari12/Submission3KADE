package com.example.footballmatchschedule.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.footballmatchschedule.Model.League
import com.example.footballmatchschedule.R
import com.example.footballmatchschedule.api.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*

class Detail : AppCompatActivity() {

    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var idLeague: String
    lateinit var imageView: ImageView
    private lateinit var deskripsi: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail_league)

        val nama = intent.getStringExtra(EXTRA_NAMA)
        val gambar = intent.getIntExtra(EXTRA_GAMBAR, 0)
        //val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)
        //val idLeague = intent.getStringExtra(EXTRA_ID)
        val idLeague = "4328"

        Log.d("id league ", idLeague)

        //val description =
        Log.d("gambar", gambar.toString())
        // val image = findViewById<ImageView>(R.id.gambarDetail)

        //gambar?.let { Picasso.get().load(it).fit().into(image) }
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(request, gson)
        presenter.getLeagueDetail(idLeague)
        var leagues: MutableList<League> = mutableListOf()
        // deskripsi = leagues.get(0).deskription
        //Log.d("Deskripsi", deskripsi)
        Log.d("dataleague2", leagues.size.toString())

        verticalLayout() {
            lparams(width = matchParent, height = wrapContent)
            imageView(gambar) {
                id = R.id.gambarDetail
            }.lparams {
                //width = dip(150)
                //height = dip(220)
                width = wrapContent
                height = wrapContent
                bottomMargin = dip(4)
            }
            textView(nama) {
                id = R.id.tvNamaDetail

            }
            textView() {
                id = R.id.tvDeskripsiDetail
            }

        }


    }

    companion object {
        const val EXTRA_NAMA = "name"
        const val EXTRA_GAMBAR = "image"
        //const val EXTRA_DESKRIPSI = "description"
        const val EXTRA_ID = "id"

    }
}
