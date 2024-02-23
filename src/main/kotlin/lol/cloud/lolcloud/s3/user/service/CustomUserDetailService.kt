package lol.cloud.lolcloud.s3.user.service

import lol.cloud.lolcloud.s3.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = lol.cloud.lolcloud.s3.user.domain.User

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {

        return userRepository.findOneWithAuthoritiesByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("유저 정보가 없습니다.")

    }

    fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .authorities(this.authorities.map { SimpleGrantedAuthority(it.authority.authorityName) }.toList())
            .build()
}