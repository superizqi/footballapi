package com.dicoding.kotlinacademy.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.*
import com.dicoding.kotlinacademy.R.array.league
import com.dicoding.kotlinacademy.R.color.colorAccent
import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.model.Team
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
            topPadding = dip(20)
            leftPadding = dip(20)
            rightPadding = dip(20)

            button("Last Match"){
                onClick {
                    toast("Last Match Cuy")
                    startActivity<lastMatch>("id" to 5)
                }
            }

            button("Next Match"){
                onClick {
                    toast("Next Match Cuy")
                    startActivity<nextMatch>()
                }

            }

//            textView {
//                text = "Sat, 29 Oct 2018"
//                textSize = 20f
//                textColor = Color.BLACK
//                textAlignment = View.TEXT_ALIGNMENT_CENTER //CENTER can be INHERIT GRAVITY TEXT_START TEXT_END VIEW_START VIEW_END
//            }
//
//            linearLayout {
//                topPadding = dip(20)
//                orientation = LinearLayout.HORIZONTAL
//                gravity = Gravity.CENTER
//
//
//                textView {
//                    padding = dip(10)
//                    text = "Manchester U"
//                    textSize = 10f
//                    textColor = Color.BLACK
//                    textAlignment = View.TEXT_ALIGNMENT_CENTER //CENTER can be INHERIT GRAVITY TEXT_START TEXT_END VIEW_START VIEW_END
//                }
//
//                textView {
//                    leftPadding = dip(20)
//                    rightPadding = dip(20)
//                    text = " 10 VS 5"
//                    textSize = 15f
//                    textColor = Color.BLACK
//                    textAlignment = View.TEXT_ALIGNMENT_CENTER //CENTER can be INHERIT GRAVITY TEXT_START TEXT_END VIEW_START VIEW_END
//                }
//
//                textView {
//                    padding = dip(10)
//                    text = "Chelsea"
//                    textSize = 10f
//                    textColor = Color.BLACK
//                    textAlignment = View.TEXT_ALIGNMENT_CENTER //CENTER can be INHERIT GRAVITY TEXT_START TEXT_END VIEW_START VIEW_END
//                }
//            }

            spinner = spinner ()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }

//        ambil dari array string di values
        val spinnerItems = resources.getStringArray(league)
//        ui spinndernya
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
//        masukkan ui ke spinner
        spinner.adapter = spinnerAdapter

//        inisiasi adapter
        adapter = MainAdapter(teams)
//        list team adalah recycler view,
//        memasukkan adapter recyclerView sesuai dengan adapter yang telah kita set sebelumnya
        listTeam.adapter = adapter

//        inisiasi api
        val request = ApiRepository()

//        inisiasi gson
        val gson = Gson()

//        inisiasi presenter
        presenter = MainPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }
    override fun hideLoading() {
        progressBar.invisible()
    }
    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}