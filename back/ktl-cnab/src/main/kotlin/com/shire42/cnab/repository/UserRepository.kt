package com.shire42.cnab.repository

import com.shire42.cnab.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, UUID> {

    fun findByUsername(username: String): Optional<User>

    fun findUserByRefreshToken(refreshToken: String): Optional<User>

    fun findUserById(userId: UUID): Optional<User>

    fun findUserByUsername(username: String?): Optional<User>

    fun deleteUserById(userId: UUID)

}