package com.sos.joblogic.navigation

sealed class Destinations(val route: String) {
    object Home : Destinations("home")
    object Buy : Destinations("buy")
    object Sell : Destinations("sell")
    object Call : Destinations("call")
}