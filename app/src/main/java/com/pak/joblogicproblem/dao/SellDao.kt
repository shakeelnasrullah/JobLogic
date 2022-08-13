package com.pak.joblogicproblem.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pak.joblogicproblem.models.BuyerItem
import com.pak.joblogicproblem.models.BuyerList


@Dao
interface SellDao {

    @Insert
    //@JvmSuppressWildcards
    suspend fun insertItems(items: List<BuyerItem>)

    @Query("SELECT * FROM ItemToSell")
    suspend fun getItems(): List<BuyerItem>
}