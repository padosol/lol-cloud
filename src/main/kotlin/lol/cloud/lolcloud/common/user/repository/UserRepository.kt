package lol.cloud.lolcloud.common.user.repository

import lol.cloud.lolcloud.common.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>{

    fun findOneWithAuthoritiesByEmail(email: String): User?
    fun findUserByEmail(email: String): User?

}