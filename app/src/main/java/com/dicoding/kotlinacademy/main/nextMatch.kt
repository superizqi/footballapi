package com.dicoding.kotlinacademy.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import com.dicoding.kotlinacademy.model.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class nextMatch : AppCompatActivity(), MainView {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamList(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
            topPadding = dip(20)
            leftPadding = dip(20)
            rightPadding = dip(20)

            button("Next Match") {
                onClick { toast("Next Match Cuy") }
            }
        }
    }
}