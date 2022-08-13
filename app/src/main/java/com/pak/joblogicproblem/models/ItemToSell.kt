package com.pak.joblogicproblem.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ItemToSell")
class ItemToSell(

    @PrimaryKey
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
) {}