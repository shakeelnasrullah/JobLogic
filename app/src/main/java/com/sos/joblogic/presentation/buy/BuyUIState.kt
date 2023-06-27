package com.sos.joblogic.presentation.buy

import com.sos.joblogic.data.model.BuyResponse


sealed interface BuyUIState {
    object Loading : BuyUIState
    class ErrorState(val message: String?) : BuyUIState
    class SuccessState(val data: BuyResponse) : BuyUIState
}