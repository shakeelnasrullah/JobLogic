package com.sos.joblogic.presentation.home

import com.sos.joblogic.data.model.BuyListItem


sealed interface HomeEvents {

    object GoToBuyScreen : HomeEvents
    object GoToSellScreen : HomeEvents
    object GoToCallScreen : HomeEvents
    object AddSellList : HomeEvents

}