package com.sos.joblogic.presentation.buy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sos.joblogic.data.onError
import com.sos.joblogic.data.onLoading
import com.sos.joblogic.data.onSuccess
import com.sos.joblogic.domain.usecases.GetBuyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyViewModel @Inject constructor(
    private val getBuyListUseCase: GetBuyListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BuyUIState>(BuyUIState.Loading)
    val uiState: StateFlow<BuyUIState> = _uiState.asStateFlow()

    fun onEvent(event: BuyEvents) {
        when (event) {
            is BuyEvents.GetBuyList -> {
                getBuyList()
            }

        }
    }

    private fun getBuyList() {
        viewModelScope.launch {
            getBuyListUseCase.invoke(Unit).collect { dataResource ->

                dataResource.onLoading {
                    _uiState.value = BuyUIState.Loading
                }
                dataResource.onSuccess {
                    _uiState.value = BuyUIState.SuccessState(this.data)
                }
                dataResource.onError {
                    _uiState.value = BuyUIState.ErrorState(this.exception.message)
                }

            }
        }
    }
}