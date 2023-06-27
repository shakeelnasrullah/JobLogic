package com.sos.joblogic.domain.repository

import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.api.ApiService
import com.sos.joblogic.data.callApi
import com.sos.joblogic.data.data
import com.sos.joblogic.data.isEmptyResponse
import com.sos.joblogic.data.model.BuyResponse
import com.sos.joblogic.data.model.CallResponse
import com.sos.joblogic.data.succeeded
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class JobLogicRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : JobLogicRepository {


    override fun getCallApi(): Flow<DataResource<CallResponse>>  = flow{
        emit(DataResource.Loading)
        val result = callApi {  apiService.getCallList()}
        if(result.data.isEmptyResponse()){
            emit(DataResource.Empty)
        }else if (result.succeeded()){
            emit(DataResource.Success(result.data!!))
        }else {
            emit(DataResource.Error<Throwable>(RuntimeException("Not Found")))
        }
    }

    override fun getBuyApi(): Flow<DataResource<BuyResponse>> = flow {
        emit(DataResource.Loading)
        val result = callApi {  apiService.getBuyList()}
        if(result.data.isEmptyResponse()){
            emit(DataResource.Empty)
        }else if (result.succeeded()){
            emit(DataResource.Success(result.data!!))
        }else {
            emit(DataResource.Error<Throwable>(RuntimeException("Not Found")))
        }
    }
}