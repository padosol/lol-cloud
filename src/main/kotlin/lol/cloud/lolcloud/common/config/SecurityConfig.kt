package lol.cloud.lolcloud.common.config

import lol.cloud.lolcloud.common.jwt.handler.JwtAccessDeniedHandler
import lol.cloud.lolcloud.common.jwt.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
) {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http {
            csrf { disable() }
            formLogin { disable() }0.


            sessionManagement { SessionCreationPolicy.STATELESS }


        }

        return http.build()
    }

}