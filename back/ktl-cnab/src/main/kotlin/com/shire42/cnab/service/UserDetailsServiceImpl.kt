package com.shire42.cnab.service

import com.shire42.cnab.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("The username $username doesn't exist") }

        val authorities = ArrayList<GrantedAuthority>()
        if (user.roles != null) {
            user.roles!!.forEach { authorities.add(SimpleGrantedAuthority(it.roleName)) }
        }
        return User(
            user.username,
            user.password,
            authorities
        )
    }
}