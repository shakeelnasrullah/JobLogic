package com.pak.joblogicproblem.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pak.joblogicproblem.repositories.ContactRepositoryImpl
import com.pak.joblogicproblem.view.ContactsViewModel

class ContactViewModelFactory( private val repository: ContactRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel( repository) as T
    }
}