package com.sos.joblogic.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sos.joblogic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolBar(title: Int, isHome: Boolean, navController: NavController) {

    Surface(
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = colorResource(id = R.color.purple_700)),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = title), color = Color.White)
                }
            },

            navigationIcon = {
                if (!isHome) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }

            },
            actions = {
                IconButton(onClick = { /* Handle action button click */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = Color.White)
                }
            }
        )
    }
}