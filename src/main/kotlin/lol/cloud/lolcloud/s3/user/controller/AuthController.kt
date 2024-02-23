package lol.cloud.lolcloud.s3.user.controller

import jakarta.validation.Valid
import lol.cloud.lolcloud.s3.jwt.TokenProvider
import lol.cloud.lolcloud.s3.jwt.dto.response.JwtResponse
import lol.cloud.lolcloud.s3.jwt.filter.JwtFilter
import lol.cloud.lolcloud.s3.user.dto.request.LoginRequest
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


    /**
     * 로그인 로직
     */
    @PostMapping("/authenticate")
    fun authorize(
        @RequestBody @Valid loginRequest: LoginRequest,
    ) : ResponseEntity<JwtResponse> {

        val token = UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)

        val authentication = authenticationManagerBuilder.getObject().authenticate(token)
        SecurityContextHolder.getContext().authentication = authentication

        val jwt: String = tokenProvider.createToken(authentication)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer $jwt")

        return ResponseEntity<JwtResponse>(JwtResponse(jwt, loginRequest.email), httpHeaders, HttpStatus.OK)
    }

}