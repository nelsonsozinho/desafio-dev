package com.shire42.cnab.service

import com.shire42.cnab.controller.error.TokenRefreshException
import com.shire42.cnab.controller.error.UserWithSameLoginException
import com.shire42.cnab.controller.rest.RefreshTokenRest
import com.shire42.cnab.model.User
import com.shire42.cnab.repository.UserRepository
import com.shire42.cnab.utils.JwtUtils
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtUtils: JwtUtils
) {

    fun saveUser(user: User): User {
        val userSameLogin = userRepository.findUserByUsername(user.username)
        if(userSameLogin.isEmpty)
            return userRepository.save(user)

        throw UserWithSameLoginException(user.username!!, "You already has account")
    }

    fun findUserByRefreshToken(refreshToken: String): Optional<User> {
        return userRepository.findUserByRefreshToken(refreshToken)
    }

    fun createRefreshToken(userId: UUID): RefreshTokenRest {
        var user = userRepository.findUserById(userId).orElseThrow()
        return generateRefreshToken(user)
    }

    fun updateRefreshToken(refreshToken: String): RefreshTokenRest {
        val user = userRepository.findUserByRefreshToken(refreshToken).orElseThrow()
        return generateRefreshToken(user)
    }

    fun verifyInspiration(rest: RefreshTokenRest): RefreshTokenRest {
        var user = userRepository.findUserByRefreshToken(rest.refreshToken!!).orElseThrow()
        if(rest.expiredDate!! < Instant.now()) {
            val refreshToken = user.refreshToken
            user.refreshToken = ""
            userRepository.save(user)
            throw TokenRefreshException(refreshToken!!, "Refresh token was expired. Please make a new signin request")
        }

        return rest
    }

    fun findUserById(id: UUID): User {
        return userRepository.findUserById(id).orElseThrow()
    }

    fun findUserByUserName(username: String): User {
        return userRepository.findUserByUsername(username).orElseThrow()
    }

    fun deleteUser(id: UUID) {
        userRepository.deleteUserById(id)
    }

    private fun generateRefreshToken(user: User): RefreshTokenRest {
        var tokenResponse = RefreshTokenRest(
            refreshToken = jwtUtils.generateRefreshToken(user.username!!),
            expiredDate = jwtUtils.getExpirationDate().toInstant(),
            token = jwtUtils.generateToken(user.username!!)
        )

        user.refreshToken = tokenResponse.refreshToken
        userRepository.save(user)

        return tokenResponse
    }

}