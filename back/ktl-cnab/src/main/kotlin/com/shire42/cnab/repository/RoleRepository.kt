package com.shire42.cnab.repository

import com.shire42.cnab.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoleRepository: JpaRepository<Role, UUID> {

    fun findByRoleName(roleName: String): Role

}