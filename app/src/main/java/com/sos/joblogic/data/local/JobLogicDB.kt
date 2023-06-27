package com.sos.joblogic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sos.joblogic.data.model.BuyListItem

@Database(entities = [BuyListItem::class], version = 1)
abstract class JobLogicDB : RoomDatabase() {

abstract fun getRecordDao() : RecordDao
}