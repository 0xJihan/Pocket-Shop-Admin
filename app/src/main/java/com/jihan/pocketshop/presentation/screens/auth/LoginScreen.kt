package com.jihan.pocketshop.presentation.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jihan.pocketshop.domain.utils.UiState
import com.jihan.pocketshop.domain.utils.toast
import com.jihan.pocketshop.domain.utils.validateUserCredentials
import com.jihan.pocketshop.domain.viewmodel.AuthViewmodel
import com.jihan.pocketshop.presentation.components.EditText
import com.jihan.pocketshop.presentation.components.InputType
import com.jihan.pocketshop.presentation.components.MyButton
import com.jihan.pocketshop.presentation.components.Shaker
import io.eyram.iconsax.IconSax
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
    goToSignupScreen: () -> Unit,
    goToMainScreen: (String) -> Unit,
    authViewmodel: AuthViewmodel= koinInject()
) {

    val loginResponse by authViewmodel.loginResponse.collectAsStateWithLifecycle()
    var loading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(loginResponse) {
    when (val state =loginResponse) {
        is UiState.Success -> {
            state.data.message.toast(context)
            goToMainScreen(state.data.token)
            loading = false
        }
        is UiState.Error -> {
            state.message.toast(context)
            loading = false
        }

        UiState.Empty -> {}
        UiState.Loading -> {
            loading=true
        }
    }
    }


    Box{


    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        var email by rememberSaveable  { mutableStateOf("") }
        var password by rememberSaveable  { mutableStateOf("") }

        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary)
        Text("Login to your account", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(20.dp))

        EditText(email, "Name", leadingIcon = IconSax.Linear.User) { email = it }
        EditText(
            password,
            "Password",
            leadingIcon = IconSax.Linear.Lock,
            inputType = InputType.PASSWORD,
            imeAction = ImeAction.Done
        ) { password = it }


        Spacer(Modifier.height(10.dp))



        MyButton("Login") {
            val pair = validateUserCredentials(
                email = email,
                password = password
            )
            if (pair.first) {
                authViewmodel.login(email, password)
            } else {
                pair.second.toast(context)
            }
        }

    }

        TextButton(onClick = goToSignupScreen, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)) { Text("Don't have an account? Signup Now") }
    }
}