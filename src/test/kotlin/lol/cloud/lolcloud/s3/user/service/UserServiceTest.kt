package lol.cloud.lolcloud.s3.user.service

import lol.cloud.lolcloud.s3.user.domain.User
import lol.cloud.lolcloud.s3.user.repository.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
) {


    @BeforeEach
    fun clear(){
        userRepository.deleteAll()
    }


    @Test
    @DisplayName("회원가입 실시")
    fun signUp() {

        // given
        val user = User(
            email = "test@test.com",
            password = "test",
            username = "tester",
            LocalDateTime.now()
        )

        userService.signup(user)

        // when
        val findUser = userRepository.findUserByEmail("test@test.com")
            ?: throw IllegalArgumentException("존재하지 않는 유저 입니다.")

        // then
        Assertions.assertThat(findUser.email).isEqualTo("test@test.com")

    }









}