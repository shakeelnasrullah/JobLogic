package com.pak.joblogicproblem.views.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pak.joblogicproblem.db.DBConnection
import com.pak.joblogicproblem.models.BuyerItem
import com.pak.joblogicproblem.models.BuyerList
import com.pak.joblogicproblem.models.PhoneContact
import com.pak.joblogicproblem.repositories.ContactRepositoryImpl
import com.pak.joblogicproblem.utils.Response
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val repository: ContactRepositoryImpl
) : ViewModel() {

    val list = MutableLiveData<List<BuyerItem>>()


    fun getSellingItemsFromDB(context: Context) {
        val dbConnection: DBConnection = DBConnection.getDatabase(context.applicationContext)
        viewModelScope.launch {
            list.value = dbConnection.sellDao().getItems()
        }

    }

    fun invokeCallAPI() {
        viewModelScope.launch {
            repository.getContacts()
        }
    }

    fun invokeBuyAPI() {

        viewModelScope.launch {
            repository.getBuyList()
        }
    }

    val contacts: LiveData<Response<PhoneContact>>
        get() = repository.contacts

    val buyList: LiveData<Response<BuyerList>>
        get() = repository.buyerList
}