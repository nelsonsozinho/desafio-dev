package com.shire42.cnab.controller.error

import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@RestControllerAdvice
class RestControllerAdvice : ResponseEntityExceptionHandler() {

    val TRACE = "trace"

    @Value("\${reflectoring.trace:false}")
    private val printStackTrace = false

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Validation error. Check 'errors' field for details."
        )
        for (fieldError in ex.bindingResult.fieldErrors) {
            errorResponse.addValidationError(fieldError.field, fieldError.defaultMessage)
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse)
    }

    @ExceptionHandler(NoSuchElementFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElementFoundException(
        itemNotFoundException: NoSuchElementFoundException?,
        request: WebRequest?
    ): ResponseEntity<Any?>? {
        return buildErrorResponse(itemNotFoundException!!,  HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleAllUncaughtException(exception: Exception?, request: WebRequest?): ResponseEntity<Any?>? {
        exception?.printStackTrace()
        return buildErrorResponse(exception!!, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request!!)
    }

    private fun buildErrorResponse(
        exception: java.lang.Exception,
        httpStatus: HttpStatus?,
        request: WebRequest?
    ): ResponseEntity<Any?>? {
        return buildErrorResponse(exception, exception.message!!, httpStatus!!, request!!)
    }

    private fun buildErrorResponse(
        exception: java.lang.Exception,
        message: String,
        httpStatus: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any?>? {
        val errorResponse = ErrorResponse(httpStatus.value(), message)
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.stackTrace = ExceptionUtils.getStackTrace(exception)
        }
        return ResponseEntity.status(httpStatus).body(errorResponse)
    }

    private fun isTraceOn(request: WebRequest): Boolean {
        val value = request.getParameterValues(TRACE)
        return (Objects.nonNull(value)
                && value!!.isNotEmpty() && value[0].contentEquals("true"))
    }

    override fun handleExceptionInternal(
        ex: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return super.handleExceptionInternal(ex, body, headers, status, request)
    }
}