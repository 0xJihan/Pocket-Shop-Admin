package com.jihan.pocketshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jihan.pocketshop.domain.utils.Datastore
import com.jihan.pocketshop.domain.utils.collectAsStateNotNull
import com.jihan.pocketshop.domain.utils.collectAsStateWithLifecycleNotNull
import com.jihan.pocketshop.domain.utils.setNetworkListenerContent
import com.jihan.pocketshop.domain.utils.toast
import com.jihan.pocketshop.domain.viewmodel.LanguageViewmodel
import com.jihan.pocketshop.presentation.PocketApp
import com.jihan.pocketshop.presentation.screens.MainScreen
import com.michaelflisar.composethemer.ComposeTheme
import org.koin.android.ext.android.inject
import java.util.Locale


class MainActivity : ComponentActivity() {


    private val languageViewmodel by inject<LanguageViewmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setNetworkListenerContent {

            val baseTheme = Datastore.baseTheme.collectAsStateNotNull()
            val dynamic = Datastore.dynamic.collectAsStateWithLifecycleNotNull()
            val theme =
                Datastore.themeKey.collectAsStateWithLifecycleNotNull()// the key of a registered theme
            val state = ComposeTheme.State(baseTheme, dynamic, theme)
            ComposeTheme(state = state) {


                PocketApp(languageViewmodel)
                val currentLanguage by languageViewmodel.language.collectAsStateWithLifecycle()
                SetLanguage(currentLanguage)


            }
        }
        handleBackPress()

    }

    private fun handleBackPress() {
        var backPressedTime = 0L
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime < 3000) {
                    finish()
                } else {
                    "Press back again to exit".toast(this@MainActivity)
                    backPressedTime = currentTime
                }
            }
        })
    }


    @Composable
    private fun SetLanguage(position: Int) {
        val locale = Locale(if (position == 0) "en" else "bn")
        val configuration = LocalConfiguration.current
        configuration.setLocale(locale)
        val displayMetrics = this.resources.displayMetrics
        resources.updateConfiguration(configuration, displayMetrics)
    }
}



