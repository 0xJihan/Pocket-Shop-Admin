package com.jihan.pocketshop.domain.api

import retrofit2.http.POST

interface AuthApi {

    @POST
    suspend fun login(email: String, password: String): String

    @POST
    suspend fun signup(email: String, password: String, name: String): String

}