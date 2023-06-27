package com.sos.joblogic.data.model


import com.google.gson.annotations.SerializedName

data class CallListItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String
)