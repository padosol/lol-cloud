package lol.cloud.lolcloud.s3.querydsl

import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles


@DataJpaTest
@ActiveProfiles("test")
class QueryDslTest @Autowired constructor(
    private val testEntityManager: TestEntityManager,
    private var entityManager: EntityManager
){

    @BeforeEach
    fun init() {
        entityManager = testEntityManager.entityManager
    }


    @Test
    fun 유저회원가입() {

        



    }

}