package com.pak.joblogicproblem.services

import com.pak.joblogicproblem.models.BuyerList
import com.pak.joblogicproblem.models.PhoneContact
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("/imkhan334/demo-1/call")
    suspend fun getContacts() : Response<PhoneContact>

    @GET("/imkhan334/demo-1/buy")
    suspend fun getBuyers() : Response<BuyerList>
}