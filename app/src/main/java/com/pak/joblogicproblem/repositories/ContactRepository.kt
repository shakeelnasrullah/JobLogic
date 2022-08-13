package com.pak.joblogicproblem.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pak.joblogicproblem.models.BuyerList
import com.pak.joblogicproblem.models.Contact
import com.pak.joblogicproblem.models.PhoneContact
import com.pak.joblogicproblem.services.ApiService
import com.pak.joblogicproblem.utils.Response
import javax.inject.Inject

interface ContactRepository {
    suspend fun getContacts()
    suspend fun getBuyList()
}

class  ContactRepositoryImpl @Inject constructor ( private val apiService: ApiService) : ContactRepository{

    private val phoneContacts = MutableLiveData<Response<PhoneContact>>()

    val contacts : LiveData<Response<PhoneContact>>
        get() = phoneContacts


    private val buyList = MutableLiveData<Response<BuyerList>>()
    val buyerList : LiveData<Response<BuyerList>> get() = buyList


    override suspend fun getContacts() {
        val result = apiService.getContacts()
        if (result.body() != null) {
            phoneContacts.postValue(Response.Success(result.body()))
        }else
            phoneContacts.postValue(Response.Failure(result.message()))
    }

    override suspend fun getBuyList() {
        val result = apiService.getBuyers()
        if(result.body() != null){
            buyList.postValue(Response.Success(result.body()))
        }else{
            phoneContacts.postValue(Response.Failure(result.message()))
        }
    }

}