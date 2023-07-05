package com.shire42.cnab.controller.error

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*


@JsonInclude(JsonInclude.Include.NON_NULL)
open class ErrorResponse {

    var status = 0
    var message: String? = null
    var stackTrace: String? = null
    var errors: MutableList<ValidationError>? = null

    constructor() {}
    constructor(statusValue: Int, message: String) {
        this.status = statusValue
        this.message = message
    }

    fun addValidationError(field: String?, message: String?) {
        if (Objects.isNull(errors)) {
            errors = ArrayList()
        }
        errors!!.add(ValidationError(field!!, message!!))
    }

}

data class ValidationError(
    val field: String,
    val message: String
)