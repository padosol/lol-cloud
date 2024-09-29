package lol.cloud.lolcloud.s3.user.domain.service

import lol.cloud.lolcloud.s3.user.application.ports.input.CreateUserUseCase
import lol.cloud.lolcloud.s3.user.application.ports.output.UserOutputPort
import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserAuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.response.UserResponse
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.mapper.UserMapper
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.AuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.mapper.UserEntityMapper
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException

typealias ApplicationUser = lol.cloud.lolcloud.s3.user.domain.model.User


@Service
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userOutputPort: UserOutputPort,
    private val userEntityMapper: UserEntityMapper,
    private val userMapper: UserMapper
) : CreateUserUseCase, UserDetailsService {

    override fun signup(user: User): UserResponse {

        userOutputPort.findUserByEmail(user.email)
            ?.let { throw RuntimeException("이미 가입되어 있는 유저 입니다.")  }

        // 암호화
        user.passwordEncode(passwordEncoder)

        val saveUser = userOutputPort.saveUser(userEntityMapper.toUserEntity(user))

        val authority = (userOutputPort.findAuthorityByAuthName("USER")
            ?: userOutputPort.saveAuthority(AuthorityEntity("USER")))

        userOutputPort.saveUserAuthority(
            UserAuthorityEntity(
                null,
                userEntityMapper.toUserEntity(saveUser),
                authority
            )
        )

        return userMapper.toUserResponse(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {

        return userOutputPort.findOneWithAuthoritiesByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("유저 정보가 없습니다.")

    }

    fun ApplicationUser.mapToUserDetails(): UserDetails =
        org.springframework.security.core.userdetails.User.builder()
            .username(this.email)
            .password(this.password)
            .authorities(this.authorityList.map { SimpleGrantedAuthority(it.authName) }.toList())
            .build()
}