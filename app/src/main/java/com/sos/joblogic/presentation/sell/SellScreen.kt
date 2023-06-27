@file:OptIn(ExperimentalMaterial3Api::class)

package com.sos.joblogic.presentation.sell

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sos.joblogic.R
import com.sos.joblogic.data.model.BuyListItem
import com.sos.joblogic.presentation.components.ErrorView
import com.sos.joblogic.presentation.components.LoadingView
import com.sos.joblogic.presentation.components.TopToolBar
import com.sos.joblogic.ui.theme.JobLogicTheme


@Composable
fun SellScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SellViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopToolBar(
                title = R.string.sell_title,
                false,
                navController
            )
        }) { innerPadding ->
        ShowContentOfScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            uiState = uiState,
            viewModel = viewModel
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val list = arrayListOf<BuyListItem>()
    list.add(BuyListItem(1, "Shakeel", 12000, 12, 1))
    list.add(BuyListItem(2, "Shakeel", 12000, 12, 1))
    list.add(BuyListItem(3, "Shakeel", 12000, 12, 1))
    JobLogicTheme {
        ShowSellList(modifier = Modifier.fillMaxSize(), list)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowContentOfScreen(
    modifier: Modifier = Modifier,
    uiState: SellUIState,
    viewModel: SellViewModel
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(SellEvents.GetSellList)
    }


    when (uiState) {
        is SellUIState.Loading -> {
            LoadingView(
                modifier = modifier

            )
        }

        is SellUIState.ErrorState -> {
            ErrorView(
                modifier = Modifier
                    .fillMaxSize(),
                error = "Something went wrong"
            )
        }

        is SellUIState.SuccessState -> {
            ShowSellList(
                modifier,
                itemList = (uiState as SellUIState.SuccessState).data
            )
        }
    }
    // Content of the screen goes here
}

@Composable
fun ShowSellList(
    modifier: Modifier = Modifier,
    itemList: List<BuyListItem>
) {


    LazyColumn(
        modifier = modifier
    ) {
        items(itemList) { buyItem ->
            Card(
                modifier = Modifier
                    .fillMaxWidth().padding(8.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.purple_500)

            ) {
                Column(Modifier.padding(18.dp)) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Name: ${buyItem.name}",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Price: ${buyItem.price}",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Quantity: ${buyItem.quantity}",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}


