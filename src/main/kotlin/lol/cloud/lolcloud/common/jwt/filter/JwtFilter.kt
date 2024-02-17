package lol.cloud.lolcloud.common.jwt.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import lol.cloud.lolcloud.common.jwt.TokenProvider
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtFilter(
    private val tokenProvider: TokenProvider,
) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        TODO("Not yet implemented")
    }

}