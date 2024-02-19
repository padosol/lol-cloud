package lol.cloud.lolcloud.common.config

import lol.cloud.lolcloud.common.jwt.filter.JwtFilter
import lol.cloud.lolcloud.common.jwt.handler.JwtAccessDeniedHandler
import lol.cloud.lolcloud.common.jwt.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.SecurityFilterChain

import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtFilter: JwtFilter,
) {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf{it.disable()}
            .exceptionHandling{
                it.accessDeniedHandler(jwtAccessDeniedHandler)
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}

            .authorizeHttpRequests{
                it
                    .requestMatchers("/api/signup", "/api/authenticate").permitAll()
            }

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}