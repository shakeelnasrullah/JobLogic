package com.sos.joblogic.domain.usecases

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.model.BuyListItem
import com.sos.joblogic.domain.repository.RoomRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddSellListUseCase(
    private val repository: RoomRepositoryImpl,
    private val dispatcher: CoroutineDispatcher
) : FlowUseCase<List<BuyListItem>, List<Long>>(dispatcher) {

    override fun execute(parameters: List<BuyListItem>):  Flow<List<Long>> {
        return flow {
            emit(repository.addSellList(parameters))
        }
    }
}