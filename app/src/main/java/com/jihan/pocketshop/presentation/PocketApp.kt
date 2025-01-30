package com.jihan.pocketshop.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jihan.pocketshop.domain.viewmodel.LanguageViewmodel
import com.jihan.pocketshop.presentation.screens.MainScreen
import com.jihan.pocketshop.presentation.screens.auth.LoginScreen
import com.jihan.pocketshop.presentation.screens.auth.SignupScreen
import kotlinx.serialization.Serializable

@Composable
fun PocketApp(languageViewmodel: LanguageViewmodel) {

    val navController = rememberNavController()

    NavHost(
        navController,Screen.LOGIN
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


}


