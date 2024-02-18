package lol.cloud.lolcloud.common.user.service

import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import lol.cloud.lolcloud.common.user.domain.Authority
import lol.cloud.lolcloud.common.user.domain.User
import lol.cloud.lolcloud.common.user.domain.UserAuthority
import lol.cloud.lolcloud.common.user.dto.response.UserResponse
import lol.cloud.lolcloud.common.user.repository.AuthorityRepository
import lol.cloud.lolcloud.common.user.repository.UserAuthorityRepository
import lol.cloud.lolcloud.common.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authorityRepository: AuthorityRepository,
    private val userAuthorityRepository: UserAuthorityRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun signup(user: User): UserResponse {

        val findUser = userRepository.findUserByEmail(user.email)
        if(findUser != null) {
            throw RuntimeException("이미 가입되어 있는 유저 입니다.")
        }

        val authority = (authorityRepository.findAuthorityByAuthorityName("USER")
            ?: authorityRepository.save(Authority("USER")))

        val saveUser = userRepository.save(
            User(
                email = user.email,
                password = passwordEncoder.encode(user.password),
                username = user.username,
                createData = user.createData,
            )
        )

        userAuthorityRepository.save(UserAuthority(null, saveUser, authority))

        return UserResponse(
            saveUser.email
        )
    }


}