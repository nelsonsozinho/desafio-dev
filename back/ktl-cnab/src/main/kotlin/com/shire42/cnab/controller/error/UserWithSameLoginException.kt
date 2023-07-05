package com.shire42.cnab.controller.error

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UserWithSameLoginException : ResponseStatusException {

    constructor(status: HttpStatus) : super(HttpStatus.BAD_REQUEST)

    constructor(login: String, reason: String) :
            super(HttpStatus.CONFLICT, "Failed for $login: $reason")

}