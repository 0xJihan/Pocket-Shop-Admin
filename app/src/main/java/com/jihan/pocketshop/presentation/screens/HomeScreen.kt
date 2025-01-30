package com.jihan.pocketshop.presentation.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.pocketshop.R
import com.jihan.pocketshop.presentation.components.CenterBox
import com.jihan.pocketshop.presentation.components.ImageSlider
import com.jihan.pocketshop.presentation.components.Indicators
import kotlinx.coroutines.delay

private val tempList = listOf(
    "https://img.lazcdn.com/us/domino/11acccfe-0265-4ce6-9efc-e17f570a6e19_BD-1976-688.jpg_2200x2200q80.jpg",
    "https://img.lazcdn.com/us/domino/b7728630-3ca7-43c0-b141-b833c8ef6792_BD-1976-688.jpg_2200x2200q80.jpg",
    ""
)


@Composable
fun HomeScreen() {

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Card (
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                8.dp
            )
        ){}
    }


}
