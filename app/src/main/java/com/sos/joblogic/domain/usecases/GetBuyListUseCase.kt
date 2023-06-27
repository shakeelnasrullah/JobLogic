package com.sos.joblogic.domain.usecases

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.model.BuyResponse
import com.sos.joblogic.domain.repository.JobLogicRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetBuyListUseCase(
    private val repository: JobLogicRepositoryImpl,
    dispatcher: CoroutineDispatcher
)  : FlowUseCase<Unit, DataResource<BuyResponse>>(dispatcher){

    override fun execute(parameters: Unit): Flow<DataResource<BuyResponse>> {
        return repository.getBuyApi()
    }
}