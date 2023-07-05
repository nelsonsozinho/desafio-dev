package com.shire42.cnab.controller.rest


import com.shire42.cnab.model.User
import java.util.*

data class UserRest(
    var id: UUID?,
    var username: String?,
    var password: String?,
    var firstName: String?,
    var lastName: String?,
) {

    fun toUser() = User(
        id = id,
        username = username,
        password = password,
        firstName = firstName,
        lastName = lastName
    )

}