package com.pak.joblogicproblem.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pak.joblogicproblem.dao.SellDao
import com.pak.joblogicproblem.models.BuyerItem

@Database(entities = [BuyerItem::class], version = 1)
abstract class DBConnection : RoomDatabase() {

    abstract fun sellDao(): SellDao

    companion object {
        @Volatile
        private var INSTANCE: DBConnection? = null

        fun getDatabase(context: Context): DBConnection {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DBConnection::class.java, "joblogicDB"
                        ).build()
                    }
                }

            }
            return INSTANCE!!

        }
    }

}