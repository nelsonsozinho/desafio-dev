package com.shire42.cnab.controller

import com.shire42.cnab.controller.rest.UserRest
import com.shire42.cnab.model.User
import com.shire42.cnab.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService,
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(@RequestBody userRest: UserRest): ResponseEntity<UserRest> {
        userRest.password = passwordEncoder.encode(userRest.password)
        val user = userRest.toUser()
        val newUser: User = userService.saveUser(user)
        return ResponseEntity(newUser.toUserRest(), HttpStatus.CREATED)
    }

}