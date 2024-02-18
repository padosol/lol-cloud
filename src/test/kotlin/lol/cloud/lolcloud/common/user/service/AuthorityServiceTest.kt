package lol.cloud.lolcloud.common.user.service

import lol.cloud.lolcloud.common.user.domain.Authority
import lol.cloud.lolcloud.common.user.repository.AuthorityRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["classpath:application-test.yml"])
class AuthorityServiceTest @Autowired constructor(
    private val authorityRepository: AuthorityRepository
) {

    @AfterEach
    fun clean() {
        authorityRepository.deleteAll()
    }

    @Test
    fun 권한이_없으면_권한추가(){

        // given && when
        val findAuth = authorityRepository.findAuthorityByAuthorityName("USER")
            ?: authorityRepository.save(Authority("USER"))

        // then
        assertThat(findAuth.authorityName).isEqualTo("USER")
    }

    @Test
    fun 권한생성() {

        // given
        val authority = Authority("USER")

        // when
        authorityRepository.save(authority)

        val findAuth = authorityRepository.findAuthorityByAuthorityName("USER")

        // then
        assertThat(findAuth?.authorityName).isEqualTo("USER")
    }

}






