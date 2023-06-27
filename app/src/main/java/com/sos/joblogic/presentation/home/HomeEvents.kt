package com.sos.joblogic.presentation.home

import com.sos.joblogic.data.model.BuyListItem


sealed interface HomeEvents {

    object AddSellList : HomeEvents

}