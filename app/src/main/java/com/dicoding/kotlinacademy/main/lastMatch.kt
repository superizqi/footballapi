package com.dicoding.kotlinacademy.main


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class lastMatch : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
            topPadding = dip(20)
            leftPadding = dip(20)
            rightPadding = dip(20)

            button("Last Match") {
                onClick { toast("Last Match Cuy") }
            }
        }


    }

}