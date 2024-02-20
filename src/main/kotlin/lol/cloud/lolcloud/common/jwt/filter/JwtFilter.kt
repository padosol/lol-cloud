package lol.cloud.lolcloud.common.jwt.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import lol.cloud.lolcloud.common.jwt.TokenProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtFilter(
    val tokenProvider: TokenProvider,
) : GenericFilterBean() {

    companion object {
        const val AUTHORIZATION_HEADER = "authorization"
    }
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {

        val httpServletRequest = request as HttpServletRequest
        val token = resolveToken(httpServletRequest)

        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

            val authentication = tokenProvider.getAuthentication(token!!)

            SecurityContextHolder.getContext().authentication = authentication

            println("토큰 저장")
        } else {
            println("토큰 정보 없음 - url: " + request.requestURI)
        }

        chain.doFilter(request, response);
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

}