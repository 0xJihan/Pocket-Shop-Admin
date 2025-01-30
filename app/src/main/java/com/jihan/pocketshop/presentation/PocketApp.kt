package com.jihan.pocketshop.presentation

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jihan.pocketshop.domain.utils.Datastore
import com.jihan.pocketshop.domain.utils.collectAsStateWithLifecycle
import com.jihan.pocketshop.domain.viewmodel.LanguageViewmodel
import com.jihan.pocketshop.presentation.components.CenterBox
import com.jihan.pocketshop.presentation.components.OrbitLoading
import com.jihan.pocketshop.presentation.screens.MainScreen
import com.jihan.pocketshop.presentation.screens.auth.LoginScreen
import com.jihan.pocketshop.presentation.screens.auth.SignupScreen
import kotlinx.serialization.Serializable

@Composable
fun PocketApp(languageViewmodel: LanguageViewmodel) {

    val navController = rememberNavController()
    val token by Datastore.token.collectAsStateWithLifecycle()
    var startDestination : Screen by remember { mutableStateOf(Screen.LOADING) }
    NavHost(
        navController, startDestination = startDestination
    ){
        composable<Screen.LOGIN>(
            popEnterTransition = { slideInHorizontally { -it } },
            exitTransition = { slideOutHorizontally { -it } }
        ) {
            LoginScreen(
                goToSignupScreen = {
                    navController.navigate(Screen.SIGNUP)
                },
                goToMainScreen = {
                    languageViewmodel.updateToken(it)
                    navController.navigate(Screen.MAIN){
                        popUpTo(Screen.LOGIN){
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable<Screen.SIGNUP>(
            enterTransition = { slideInHorizontally{it} },
            exitTransition = { slideOutHorizontally{it} },


        ) {
            SignupScreen(
                backToLogin = {
                    navController.navigateUp()
                },
                goToMainScreen = {
                    languageViewmodel.updateToken(it)
                    navController.navigate(Screen.MAIN){
                        popUpTo(Screen.LOGIN){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Screen.MAIN>(

        ) {
            MainScreen(languageViewmodel)
        }

        composable<Screen.LOADING> { CenterBox {
            OrbitLoading(Modifier.size(150.dp))
            Text("Loading...")
        }}
    }


    LaunchedEffect(token) {
        Log.d("PocketApp", "Token: $token")
        startDestination = if (token == null) {
            Screen.LOGIN
        } else {
            Screen.MAIN
        }
    }

}


@Serializable
sealed interface Screen {

    @Serializable
    data object LOGIN : Screen
    @Serializable
    data object SIGNUP : Screen
    @Serializable
    data object MAIN : Screen

    @Serializable
    data object LOADING : Screen


}


