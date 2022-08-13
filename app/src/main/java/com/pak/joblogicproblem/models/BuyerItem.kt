package com.pak.joblogicproblem.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class BuyerItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
)