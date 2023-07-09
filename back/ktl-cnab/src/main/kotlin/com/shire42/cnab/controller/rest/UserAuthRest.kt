package com.shire42.cnab.controller.rest

import java.util.Date

data class UserAuthRest (
    val firstName: String,
    val lastName: String,
    val refreshToken: String,
    val expiration: Date,
    val token: String,
    val prefix: String,
    val userName: String,
    val roles: List<String?>? = emptyList()
){}