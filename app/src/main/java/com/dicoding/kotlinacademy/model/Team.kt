package com.dicoding.kotlinacademy.model

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 1/23/18.
 */
data class Team(
        @SerializedName("strEvent")
        var teamId: String? = null,

        @SerializedName("strEvent")
        var teamName: String? = null,

        @SerializedName("strEvent")
        var teamBadge: String? = null
        )