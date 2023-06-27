package com.sos.joblogic.domain.usecases

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.model.BuyResponse
import com.sos.joblogic.data.model.CallResponse
import com.sos.joblogic.domain.repository.JobLogicRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetCallListUseCase(
    private val repository: JobLogicRepositoryImpl,
    dispatcher: CoroutineDispatcher
)  : FlowUseCase<Unit, DataResource<CallResponse>>(dispatcher){

    override fun execute(parameters: Unit): Flow<DataResource<CallResponse>> {
        return repository.getCallApi()
    }
}