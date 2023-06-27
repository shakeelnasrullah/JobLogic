package com.sos.joblogic.domain.usecases

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.model.BuyListItem
import com.sos.joblogic.domain.repository.RoomRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetSellListUseCase(
    private val repository: RoomRepositoryImpl,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, DataResource<List<BuyListItem>>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<DataResource<List<BuyListItem>>> {
        return repository.getSellList()
    }
}