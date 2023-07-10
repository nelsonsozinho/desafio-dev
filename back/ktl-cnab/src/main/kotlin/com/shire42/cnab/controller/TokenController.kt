package com.shire42.cnab.controller

import com.shire42.cnab.controller.error.TokenRefreshException
import com.shire42.cnab.controller.rest.RefreshTokenRest
import com.shire42.cnab.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/token"], produces = [MediaType.APPLICATION_JSON_VALUE])
@Api(value = "Refresh token API",
    description = "This is the process used to renew the authentication token", produces = "application/json")
class TokenController(
    private val userService: UserService
) {

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update the user token", produces = "application/json")
    fun refreshToken(@RequestBody body: RefreshTokenRest): ResponseEntity<RefreshTokenRest> {
        return userService.findUserByRefreshToken(body.refreshToken!!)
            .map { userService::verifyInspiration }
            .map {
                val refreshTokenRest = userService.updateRefreshToken(body.refreshToken)
                return@map ResponseEntity.ok(refreshTokenRest)
            }.orElseThrow {
                TokenRefreshException(body.refreshToken, "Refresh token is not in the database")
            }
    }

}