package com.shire42.cnab.filter


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.shire42.cnab.service.UserService
import com.shire42.cnab.utils.JwtUtils
import com.shire42.cnab.utils.SecurityProperties
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val securityProperties: SecurityProperties,
    private val jwtUtils: JwtUtils,
    private val userService: UserService
): UsernamePasswordAuthenticationFilter() {

    private var mapper = ObjectMapper()

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?
    ): Authentication {
        return try {
            val mapper = jacksonObjectMapper()
            val creds = mapper.readValue<com.shire42.cnab.model.User>(req.inputStream)
            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    creds.username,
                    creds.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw AuthenticationServiceException(e.message)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        auth: Authentication
    ) {
        val authClaims: MutableList<String> = mutableListOf()
        val tokenMap = HashMap<String, String>()
        var dbUser = userService.findUserByUserName(auth.name)

        auth.authorities?.let { authorities ->
            authorities.forEach { claim -> authClaims.add(claim.toString()) }
        }

        val token = jwtUtils.generateToken(auth.name)
        userService.createRefreshToken(dbUser.id!!)
        dbUser = userService.findUserById(dbUser.id!!)

        tokenMap["Token"] = token
        tokenMap["RefreshToken"] = dbUser.refreshToken!!
        tokenMap["Prefix"] = securityProperties.tokenPrefix.trim()
        tokenMap["Expiration"] = Date().add(Calendar.DAY_OF_MONTH, securityProperties.expirationTime).toString()

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.addHeader(securityProperties.headerString, securityProperties.tokenPrefix + token)
        response.writer.write(mapper.writeValueAsString(tokenMap))
    }

    fun Date.add(field: Int, amount: Int): Date {
        Calendar.getInstance().apply {
            time = this@add
            add(field, amount)
            return time
        }
    }
}
