package com.sos.joblogic.data.api

import com.sos.joblogic.data.model.BuyResponse
import com.sos.joblogic.data.model.CallResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/imkhan334/demo-1/buy")
    suspend fun getBuyList(): BuyResponse

    @GET("/imkhan334/demo-1/call")
    suspend fun getCallList() : CallResponse
}