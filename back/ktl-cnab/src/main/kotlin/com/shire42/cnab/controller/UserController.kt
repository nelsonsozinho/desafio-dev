package com.shire42.cnab.controller

import com.shire42.cnab.controller.rest.UserRest
import com.shire42.cnab.model.User
import com.shire42.cnab.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
@Api(value = "New User",
    description = "API used to create a new user", produces = "application/json")
class UserController(
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService,
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new user. By default the user receive the role of ADMIN by default", produces = "application/json")
    fun saveUser(@RequestBody userRest: UserRest): ResponseEntity<UserRest> {
        userRest.password = passwordEncoder.encode(userRest.password)
        val user = userRest.toUser()
        val newUser: User = userService.saveUser(user)
        return ResponseEntity(newUser.toUserRest(), HttpStatus.CREATED)
    }

}