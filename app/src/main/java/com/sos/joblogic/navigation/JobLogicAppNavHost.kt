package com.sos.joblogic.navigation

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sos.joblogic.presentation.buy.BuyScreen
import com.sos.joblogic.presentation.call.CallScreen
import com.sos.joblogic.presentation.home.HomeScreen
import com.sos.joblogic.presentation.sell.SellScreen

@Composable
fun JobLogicAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.Home.route
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        homeGraph(navController)
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(Destinations.Home.route) {
        HomeScreen(modifier = Modifier.fillMaxSize(), navController)
    }
    composable(Destinations.Buy.route) {
        BuyScreen(modifier = Modifier.fillMaxSize(), navController)
    }
    composable(Destinations.Sell.route) {
        SellScreen(modifier = Modifier.fillMaxSize(), navController)
    }
    composable(Destinations.Call.route) {
        CallScreen(modifier = Modifier.fillMaxSize(), navController)
    }
}

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink =
        NavDeepLinkRequest.Builder.fromUri(NavDestination.createRoute(route).toUri()).build()
    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}

fun <T> NavBackStackEntry.parcelableData(key: String): T? {
    return arguments?.getParcelable(key) as? T
}