package com.jihan.pocketshop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jihan.pocketshop.R
import com.jihan.pocketshop.domain.viewmodel.LanguageViewmodel
import com.jihan.pocketshop.presentation.components.CenterBox
import com.jihan.pocketshop.presentation.screens.auth.LoginScreen
import io.eyram.iconsax.IconSax


@Composable
fun MainScreen(languageViewmodel: LanguageViewmodel) {

    val list = remember {
        listOf(
            NavItem("Home", IconSax.Linear.Home, Screen.Home),
            NavItem("Profile", IconSax.Linear.Login, Screen.Profile),
            NavItem("Settings", IconSax.Linear.Settings, Screen.Settings),
        )
    }

    var selectedPosition by remember { mutableIntStateOf(0) }


    Scaffold(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), bottomBar = {
        BottomAppBar {
            list.forEachIndexed { index, navItem ->
                NavigationBarItem(
                    selected = selectedPosition == index,
                    onClick = {
                        selectedPosition = index
                    },
                    icon = {
                        Icon(painterResource(id = navItem.icon), contentDescription = null)
                    },
                )
            }
        }
    }) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (list[selectedPosition].screen) {
                Screen.Home -> {
                    HomeScreen()
                }

                Screen.Profile -> {
                    CenterBox { Text(text = "Profile") }
                }
                Screen.Settings -> SettingScreen(languageViewmodel)
            }
        }

    }

}


data class NavItem(val title: String, val icon: Int, val screen: Screen)

enum class Screen {
    Home, Profile, Settings
}