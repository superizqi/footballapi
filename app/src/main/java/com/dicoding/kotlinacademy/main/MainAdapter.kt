package com.dicoding.kotlinacademy.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.kotlinacademy.R.id.*
import com.dicoding.kotlinacademy.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MainAdapter(private val teams: List<Team>)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    override fun getItemCount(): Int = teams.size
}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL



//                imageView {
//                    id = team_badge
//                }.lparams{
//                    height = dip(50)
//                    width = dip(50)
//                }

//                textView {
//                    id = team_name
//                    textSize = 16f
//                }.lparams{
//                    margin = dip(15)
//                }

                textView {
                    id = date_event
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = str_event
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
//    private val teamBadge: ImageView = view.find(team_badge)
    private val dateEvent: TextView = view.find(date_event)
    private val strEvent: TextView = view.find(str_event)
    fun bindItem(teams: Team) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        dateEvent.text = teams.teamName
        strEvent.text = teams.teamName
    }
}