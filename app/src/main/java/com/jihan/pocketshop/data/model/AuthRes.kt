package com.jihan.pocketshop.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRes(
    val message: String,
    val token: String
)