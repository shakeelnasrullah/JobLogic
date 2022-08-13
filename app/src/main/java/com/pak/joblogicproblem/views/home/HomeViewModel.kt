package com.pak.joblogicproblem.views.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.pak.joblogicproblem.db.DBConnection
import com.pak.joblogicproblem.models.BuyerItem
import com.pak.joblogicproblem.models.BuyerList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private lateinit var dbConnection: DBConnection

    public fun insertDataIntoTable(context: Context) {
        dbConnection = DBConnection.getDatabase(context.applicationContext)
        GlobalScope.launch {
            val resultList = dbConnection.sellDao().getItems()
            if (resultList.isEmpty()) {
                dbConnection.sellDao().insertItems(getBuyerItemList())
            }
        }
    }

    private fun getBuyerItemList(): BuyerList {
        val list = BuyerList()
        list.add(BuyerItem(1, "iPhone X", 150000, 1, 2))
        list.add(BuyerItem(2, "TV", 38000, 2, 2))
        list.add(BuyerItem(3, "Table", 12000, 1, 2))
        return list
    }
}