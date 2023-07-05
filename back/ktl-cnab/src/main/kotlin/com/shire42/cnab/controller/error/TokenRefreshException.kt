package com.shire42.cnab.controller.error

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class TokenRefreshException : ResponseStatusException {

    constructor(status: HttpStatus) : super(HttpStatus.NOT_FOUND)

    constructor(token: String, reason: String) :
            super(HttpStatus.NOT_FOUND, "Failed for $token: $reason")

}