package lol.cloud.lolcloud.common.user.service

import lol.cloud.lolcloud.common.user.domain.User
import lol.cloud.lolcloud.common.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {


        TODO("Not yet impleented")
    }
}