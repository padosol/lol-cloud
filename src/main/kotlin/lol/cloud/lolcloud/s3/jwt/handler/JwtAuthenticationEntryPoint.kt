package lol.cloud.lolcloud.s3.jwt.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private var log = LoggerFactory.getLogger(this.javaClass)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {

        log.info("JwtAuthenticationEntryPoint")

        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}