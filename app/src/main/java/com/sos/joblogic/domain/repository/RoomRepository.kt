package com.sos.joblogic.domain.repository

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.model.BuyListItem
import kotlinx.coroutines.flow.Flow
import javax.sql.DataSource

interface RoomRepository {

   suspend fun addSellList(list : List<BuyListItem>) : List<Long>

    fun getSellList() : Flow<DataResource<List<BuyListItem>>>
}