package com.sos.joblogic.presentation.buy

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
import com.sos.joblogic.presentation.home.HomeUIState
import com.sos.joblogic.presentation.home.HomeViewModel
import com.sos.joblogic.presentation.sell.SellEvents
import com.sos.joblogic.presentation.sell.SellUIState
import com.sos.joblogic.presentation.sell.SellViewModel
import com.sos.joblogic.presentation.sell.ShowContentOfScreen
import com.sos.joblogic.presentation.sell.ShowList
import com.sos.joblogic.ui.theme.JobLogicTheme


@Composable
fun BuyScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: BuyViewModel = hiltViewModel()
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopToolBar(
                title = R.string.buy_title,
                false,
                navController
            )
        }) { innerPadding ->
        BuyScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            uiState = uiState,
            viewModel = viewModel,
            innerPadding
        )
    }


}

@Composable
fun BuyScreenContent(
    modifier: Modifier = Modifier,
    uiState: BuyUIState,
    viewModel: BuyViewModel,
    innerPadding: PaddingValues
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(BuyEvents.GetBuyList)
    }


    when (uiState) {
        is BuyUIState.Loading -> {
            LoadingView(
                modifier = modifier

            )
        }

        is BuyUIState.ErrorState -> {
            ErrorView(
                modifier = Modifier
                    .fillMaxSize(),
                error = "Something went wrong"
            )
        }

        is BuyUIState.SuccessState -> {
            ShowBuyList(
                modifier,
                itemList = (uiState as BuyUIState.SuccessState).data
            )
        }
    }


}

@Composable
fun ShowBuyList(
    modifier: Modifier = Modifier,
    itemList: List<BuyListItem>
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(itemList) { buyItem ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.purple_700)

            ) {
                Column(Modifier.padding(8.dp)) {
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
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobLogicTheme {

    }
}