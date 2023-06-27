package com.sos.joblogic.presentation.call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sos.joblogic.data.onError
import com.sos.joblogic.data.onLoading
import com.sos.joblogic.data.onSuccess
import com.sos.joblogic.domain.usecases.GetCallListUseCase
import com.sos.joblogic.presentation.home.HomeEvents
import com.sos.joblogic.presentation.home.HomeUIState
import com.sos.joblogic.presentation.sell.SellUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val getCallListUseCase : GetCallListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CallUIState>(CallUIState.Loading)
    val uiState: StateFlow<CallUIState> = _uiState.asStateFlow()

    fun onEvent(event: CallEvents) {
        when (event) {
            is CallEvents.GetCallList -> {
                getCallList()
            }

        }
    }

    private fun getCallList() {
        viewModelScope.launch {
            getCallListUseCase.invoke(Unit).collect { dataResource ->

                dataResource.onLoading {
                    _uiState.value = CallUIState.Loading
                }
                dataResource.onSuccess {
                    _uiState.value = CallUIState.SuccessState(this.data)
                }
                dataResource.onError {
                    _uiState.value = CallUIState.ErrorState(this.exception.message)
                }

            }
        }
    }

}