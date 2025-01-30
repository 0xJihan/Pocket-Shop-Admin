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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jihan.pocketshop.presentation.components.EditText
import com.jihan.pocketshop.presentation.components.InputType
import com.jihan.pocketshop.presentation.components.MyButton
import com.jihan.pocketshop.presentation.components.Shaker
import io.eyram.iconsax.IconSax

@Composable
fun LoginScreen(
    goToSignupScreen: () -> Unit,
    goToMainScreen: () -> Unit
) {

    Box{


    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

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
            goToMainScreen()
        }

        Shaker()
    }

        TextButton(onClick = goToSignupScreen, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)) { Text("Don't have an account? Signup Now") }
    }
}