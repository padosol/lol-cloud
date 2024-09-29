package lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web

import jakarta.validation.Valid
import lol.cloud.lolcloud.s3.jwt.TokenProvider
import lol.cloud.lolcloud.s3.jwt.dto.response.JwtResponse
import lol.cloud.lolcloud.s3.jwt.filter.JwtFilter
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.request.LoginRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {

    private val log = LoggerFactory.getLogger(this.javaClass)!!
    /**
     * 로그인 로직
     */
    @PostMapping("/authenticate")
    fun authorize(
        @RequestBody @Valid loginRequest: LoginRequest,
    ) : ResponseEntity<JwtResponse> {

        log.info("[Authenticate 시작]")

        val token = UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        log.info("token: {}", token)

        val authentication = authenticationManagerBuilder.getObject().authenticate(token)
        SecurityContextHolder.getContext().authentication = authentication
        log.info("authentication: {}", authentication)

        val jwt: String = tokenProvider.createToken(authentication)
        log.info("jwt: {}", jwt)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer $jwt")
        log.info("[Authenticate 시작]")

        return ResponseEntity<JwtResponse>(JwtResponse(jwt, loginRequest.email), httpHeaders, HttpStatus.OK)
    }

}