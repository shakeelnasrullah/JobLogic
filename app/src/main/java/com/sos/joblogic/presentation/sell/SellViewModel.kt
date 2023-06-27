package com.sos.joblogic.presentation.sell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sos.joblogic.data.DataResource
import com.sos.joblogic.data.onEmpty
import com.sos.joblogic.data.onError
import com.sos.joblogic.data.onLoading
import com.sos.joblogic.data.onSuccess
import com.sos.joblogic.domain.usecases.GetSellListUseCase
import com.sos.joblogic.presentation.buy.BuyEvents
import com.sos.joblogic.presentation.buy.BuyUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(
    private val getSellListUseCase: GetSellListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SellUIState>(SellUIState.Loading)
    val uiState: StateFlow<SellUIState> = _uiState.asStateFlow()

    fun onEvent(event: SellEvents) {
        when (event) {
            is SellEvents.GetSellList -> {
                getSellListFromDB()
            }

        }
    }

    private fun getSellListFromDB() {
        viewModelScope.launch {
            getSellListUseCase.invoke(Unit).collect { dataResource ->

                dataResource.onLoading {
                    _uiState.value = SellUIState.Loading
                }
                dataResource.onSuccess {
                    _uiState.value = SellUIState.SuccessState(this.data)
                }
                dataResource.onError {
                    _uiState.value = SellUIState.ErrorState(this.exception.message)
                }

            }
        }
    }
}