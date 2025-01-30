package com.jihan.pocketshop.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihan.pocketshop.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewmodel(private val authRepository: AuthRepository) : ViewModel() {

val loginResponse = authRepository.loginResponse
val signupResponse = authRepository.signupResponse


    fun login(email: String, password: String) {
        viewModelScope.launch {
        authRepository.login(email, password)
        }
    }


    fun signup(email: String, password: String, name: String) {
        viewModelScope.launch {
        authRepository.signup(email, password, name)
        }
    }




}