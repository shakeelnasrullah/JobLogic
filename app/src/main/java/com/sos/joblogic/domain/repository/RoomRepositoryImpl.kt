package com.sos.joblogic.domain.repository

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.isEmptyResponse
import com.sos.joblogic.data.local.RecordDao
import com.sos.joblogic.data.model.BuyListItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RoomRepositoryImpl(
    private val dao: RecordDao,
    private val dispatcher: CoroutineDispatcher
) : RoomRepository {


    override suspend fun addSellList(list: List<BuyListItem>): List<Long> {
        val result = dao.insertRecord(list)
        return result.ifEmpty {
            mutableListOf<Long>()
        }

    }

    override fun getSellList(): Flow<DataResource<List<BuyListItem>>> = flow {
        emit(DataResource.Loading)
        val list = dao.getRecords()
        if (list.first().isNotEmpty()) {
            emit(DataResource.Success(list.first()))
        } else {
            emit(DataResource.Empty)
        }
    }.flowOn(dispatcher)
}