package lol.cloud.lolcloud.s3.jwt.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import lol.cloud.lolcloud.s3.jwt.TokenProvider
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean

class JwtFilter(
    private val tokenProvider: TokenProvider,
) : GenericFilterBean() {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    companion object {
        const val AUTHORIZATION_HEADER = "authorization"
    }
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {

        val httpServletRequest = request as HttpServletRequest
        val token = tokenProvider.resolveToken(httpServletRequest)

        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

            val authentication = tokenProvider.getAuthentication(token!!)

            SecurityContextHolder.getContext().authentication = authentication

            log.info("토큰 저장 - url: {}", request.requestURI)
        } else {
            log.info("토큰 정보 없음 - url: {}", request.requestURI)
        }

        chain.doFilter(request, response);
    }

}