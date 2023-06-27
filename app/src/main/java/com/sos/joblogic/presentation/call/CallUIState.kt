package com.sos.joblogic.presentation.call

import com.sos.joblogic.data.model.CallResponse


sealed interface CallUIState {
    object Loading : CallUIState
    class ErrorState(val message: String?) : CallUIState
    class SuccessState(val data: CallResponse) : CallUIState
}