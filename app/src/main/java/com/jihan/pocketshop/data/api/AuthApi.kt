package com.jihan.pocketshop.data.api

import com.jihan.pocketshop.data.model.AuthRes
import com.jihan.pocketshop.data.model.LoginRequest
import com.jihan.pocketshop.data.model.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

private const val LOGIN = "/api/v1/auth/login"
private const val SIGNUP = "/api/v1/auth/register"
interface AuthApi {

    @POST(LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthRes>

    @POST(SIGNUP)
    suspend fun signup(
        @Body signupRequest: SignupRequest
    ): Response<AuthRes>

}