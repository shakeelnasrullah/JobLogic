package com.sos.joblogic.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sos.joblogic.R
import com.sos.joblogic.navigation.Destinations
import com.sos.joblogic.presentation.components.LoadingView
import com.sos.joblogic.presentation.components.TopToolBar
import com.sos.joblogic.ui.theme.JobLogicTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = { TopToolBar(title = R.string.home_title, true, navController) }) { innerPadding ->
        HomeContents(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            uiState = uiState,
            homeViewModel = viewModel, navController
        )
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobLogicTheme {

    }
}

@Composable
fun HomeContents(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
    homeViewModel: HomeViewModel,
    navController: NavController
) {

    LaunchedEffect(key1 = Unit) {
        homeViewModel.onEvent(HomeEvents.AddSellList)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {  navController.navigate(Destinations.Call.route)},
                modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))
            ) {
                Text(text = "Call List")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {navController.navigate(Destinations.Buy.route) },
                modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))
            ) {
                Text(text = "Buy List")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Destinations.Sell.route) },
                modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))
            ) {
                Text(text = "Sell List")
            }
        }
    }
    when (uiState) {
        HomeUIState.Loading -> {
            LoadingView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }

        HomeUIState.SuccessState -> {
            // show toast or perform any action
            /*Toast.makeText(LocalContext.current, "Sell List added Successfully", Toast.LENGTH_SHORT)
                .show()*/
        }

        else -> {}
    }
}