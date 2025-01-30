package com.jihan.pocketshop.domain.repository

import com.jihan.pocketshop.domain.api.AuthApi

class AuthRepository(
    private val authApi: AuthApi
) {



    suspend fun login(email: String, password: String): String {
        return authApi.login(email, password)
    }

    suspend fun signup(email: String, password: String, name: String): String {
        return authApi.signup(email, password, name)
    }



}