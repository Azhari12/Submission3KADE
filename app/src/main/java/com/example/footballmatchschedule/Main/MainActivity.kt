package com.example.footballmatchschedule.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import com.example.footballmatchschedule.Model.Item
import com.example.footballmatchschedule.R
import com.example.footballmatchschedule.Adapter.RecyclerViewAdapter
import com.example.footballmatchschedule.api.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()
    //private var list: ArrayList<Item> = arrayListOf()
    //private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showRecyclerList()


    }

    private fun ItemClicked(items: Item) {

        Toast.makeText(this, "Clicked: ${items.name} ${items.image}", Toast.LENGTH_LONG).show()
        startActivity<Detail>(
            "name" to items.name,
            "image" to items.image,
            "id" to items.idLeague
            //"description" to items.Description
        )

    }

    private fun showRecyclerList() {
        initData()
        relativeLayout() {
            lparams(width = matchParent, height = matchParent)
            recyclerView() {
                lparams {
                    width = matchParent
                    height = matchParent
                }
                id = R.id.club_list
                layoutManager = LinearLayoutManager(context)
                adapter = RecyclerViewAdapter(
                    context,
                    items,
                    { items: Item -> ItemClicked(items) })
            }

        }


    }


    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        //val description = resources.getStringArray(R.array.info_liga)
        val id = resources.getStringArray(R.array.idLeague)
        items.clear()
        for (i in name.indices) {
            items.add(
                Item(
                    name[i],
                    image.getResourceId(i, 0),
                    id[i]
                )
            )
        }
        //Recycle the typed array
        image.recycle()
    }
}
