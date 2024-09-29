package lol.cloud.lolcloud.s3.user.domain.model

import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserTest {

    @Test
    fun 유저_패스워드_암호화_테스트() {
        val bCryptPasswordEncoder = BCryptPasswordEncoder()

        val password = "test1234"

    }

}

