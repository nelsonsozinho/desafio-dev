package com.shire42.cnab.filter

import com.shire42.cnab.utils.SecurityProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(
    authManager: AuthenticationManager,
    private val securityProperties: SecurityProperties
): BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(securityProperties.headerString)

        if (header == null || !header.startsWith(securityProperties.tokenPrefix)) {
            chain.doFilter(request, response)
            return
        }

        getAuthentication(header)?.also {
            SecurityContextHolder.getContext().authentication = it
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(securityProperties.secret.toByteArray()))
                .build()
                .parseClaimsJws(token.replace(securityProperties.tokenPrefix, ""))
            val authorities = ArrayList<GrantedAuthority>()
            (claims.body["auth"] as List<*>).forEach { role -> authorities.add(SimpleGrantedAuthority(role.toString())) }

            UsernamePasswordAuthenticationToken(claims.body.subject, null, authorities)
        } catch (e: Exception) {
            return null
        }
    }
}