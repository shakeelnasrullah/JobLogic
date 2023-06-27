package com.sos.joblogic.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sos.joblogic.data.model.BuyListItem
import com.sos.joblogic.data.onError
import com.sos.joblogic.data.onLoading
import com.sos.joblogic.data.onSuccess
import com.sos.joblogic.domain.usecases.AddSellListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addSellListUseCase: AddSellListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.AddSellList -> {
                addSellListIntoRoomDB()
            }
        }
    }

    private fun addSellListIntoRoomDB(){
        viewModelScope.launch {
            _uiState.value = HomeUIState.Loading
            addSellListUseCase.invoke(getSellList()).collect{ list ->
                if(list.isEmpty()){
                    _uiState.value = HomeUIState.ErrorState("Exception Occured While insertion")
                }else{
                    _uiState.value = HomeUIState.SuccessState
                }
            }
        }

    }

    private fun getSellList() : List<BuyListItem>{
        val list = mutableListOf<BuyListItem>()
        list.add(BuyListItem(1, "Table", 12000, 1, 2))
        list.add(BuyListItem(2, "Tv", 38000, 2, 2))
        list.add(BuyListItem(3, "iPhone X", 150000, 1, 2))
        return list
    }
}