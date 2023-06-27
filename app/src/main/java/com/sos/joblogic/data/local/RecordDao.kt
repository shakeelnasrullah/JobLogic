package com.sos.joblogic.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sos.joblogic.data.model.BuyListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record : List<BuyListItem>) : List<Long>

    @Query("select * from records")
    fun getRecords() : Flow<List<BuyListItem>>

    @Query("Delete from records")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(record: BuyListItem)
}