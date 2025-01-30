package com.jihan.pocketshop.domain.utils

import android.util.Patterns
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import retrofit2.Response


fun validateUserCredentials(
        userName: String = "Default",
        email: String,
        password: String,
        confirmPassword :String=password
    ): Pair<Boolean, String> {

        val result = Pair(true, "")

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return Pair(false, "Please Provide All Required Information")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Pair(false, "Invalid Email Address")
        } else if (password.length < 5) {
            return Pair(false, "Password should be at least 5 characters long")
        }


        return result
    }

suspend fun <T> handleResponse(
    response: Response<T>,
    stateFlow: MutableStateFlow<UiState<T>>,
) {
    if (response.isSuccessful) {
        stateFlow.emit(UiState.Success(response.body()!!))
    } else {
        val errorMessage = response.errorBody()?.let {
            JSONObject(it.charStream().readText()).getString("msg")
        } ?: response.message()
        stateFlow.emit(UiState.Error(errorMessage))
    }
}

