package com.jihan.pocketshop.presentation.screens


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.jihan.pocketshop.R
import com.jihan.pocketshop.presentation.components.CenterBox


@Composable
fun HomeScreen() {

    CenterBox {


        Text(
            stringResource(R.string.hello_android),
            fontSize = 35.sp,
        )





    }

}
