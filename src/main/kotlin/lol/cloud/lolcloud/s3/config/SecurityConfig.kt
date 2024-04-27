package lol.cloud.lolcloud.s3.config

import lol.cloud.lolcloud.s3.jwt.TokenProvider
import lol.cloud.lolcloud.s3.jwt.filter.JwtFilter
import lol.cloud.lolcloud.s3.jwt.handler.JwtAccessDeniedHandler
import lol.cloud.lolcloud.s3.jwt.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig(
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val tokenProvider: TokenProvider,
) {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .cors {}
            .exceptionHandling{
                it.accessDeniedHandler(jwtAccessDeniedHandler)
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}

            .authorizeHttpRequests{
                it
                    .requestMatchers("/api/signup", "/api/authenticate", "/api/upload", "/static/*/**").permitAll()
                    .requestMatchers("/api/buckets").hasAnyAuthority("USER", "ADMIN")
                    .anyRequest().authenticated()
            }

            .addFilterBefore(JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}