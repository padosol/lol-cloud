package lol.cloud.lolcloud.common.user.controller

import lol.cloud.lolcloud.common.jwt.TokenProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {


    @GetMapping("/authenticate")
    fun authorize(
    ) {

    }

}