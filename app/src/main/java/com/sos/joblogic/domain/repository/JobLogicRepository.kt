package com.sos.joblogic.domain.repository

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.model.BuyResponse
import com.sos.joblogic.data.model.CallResponse
import kotlinx.coroutines.flow.Flow

interface JobLogicRepository {

    fun getCallApi() : Flow<DataResource<CallResponse>>

    fun getBuyApi() : Flow<DataResource<BuyResponse>>
}