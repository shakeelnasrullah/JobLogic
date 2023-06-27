package com.sos.joblogic.presentation.sell

import com.sos.joblogic.data.model.BuyListItem
import com.sos.joblogic.data.model.BuyResponse


sealed interface SellUIState {
    object Loading : SellUIState
    class ErrorState(val message: String?) : SellUIState
    class SuccessState(val data: List<BuyListItem>) : SellUIState
}