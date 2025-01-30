package com.jihan.pocketshop.domain.repository

import android.util.Log
import com.jihan.pocketshop.data.api.AuthApi
import com.jihan.pocketshop.data.model.AuthRes
import com.jihan.pocketshop.data.model.LoginRequest
import com.jihan.pocketshop.data.model.SignupRequest
import com.jihan.pocketshop.domain.utils.Constants.TAG
import com.jihan.pocketshop.domain.utils.UiState
import com.jihan.pocketshop.domain.utils.handleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.log

class AuthRepository(
    private val authApi: AuthApi
) {

    private val _loginResponse = MutableStateFlow<UiState<AuthRes>>(UiState.Empty)
    val loginResponse = _loginResponse.asStateFlow()

    private val _signupResponse = MutableStateFlow<UiState<AuthRes>>(UiState.Empty)
    val signupResponse = _signupResponse.asStateFlow()


    suspend fun login(email: String, password: String) {
        try {
            _loginResponse.value = UiState.Loading
            val response = authApi.login(LoginRequest(email, password))
            handleResponse(response, _loginResponse)
        } catch (e: Exception) {
            _loginResponse.value = UiState.Error(e.message.toString())
            Log.e(TAG, "signup: ${e.message}", )
        }

    }


    suspend fun signup(email: String, password: String, name: String) {
        try {
            _signupResponse.value = UiState.Loading
            val response = authApi.signup(SignupRequest(email, password, name))
            handleResponse(response, _signupResponse)
        } catch (e: Exception) {
            _signupResponse.value = UiState.Error(e.message.toString())
            Log.e(TAG, "signup: ${e.message}", )
        }
    }


}