package com.sos.joblogic.presentation.home

import com.sos.joblogic.data.model.CallResponse


sealed interface HomeUIState {
    object Loading : HomeUIState
    class ErrorState(val message: String?) : HomeUIState
    object SuccessState : HomeUIState
}